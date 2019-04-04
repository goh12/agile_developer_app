package com.agiledev.agiledeveloper.services;

import android.content.Context;
import android.util.Log;

import com.agiledev.agiledeveloper.CreateProjectActivity;
import com.agiledev.agiledeveloper.LoginActivity;
import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
import com.agiledev.agiledeveloper.dataparsers.ProjectDataParser;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

public class ProjectService {

    Context context;
    ProjectDataParser parser;

    /**
     * Constructor
     * @param context Activity notar þetta service.
     */
    public ProjectService(Context context) {
        this.context = context;
        this.parser = new ProjectDataParser();
    }

    /**
     * Setur upp nýtt async kall til að logga inn notanda á server.
     * @param inputToken token fyrir verkefnið.
     * @return boolean hvort login hafi tekist
     */
    public void login(String inputToken) { //Prufu og sýnifall
        Thread t = new Thread(new Runnable() { //Búa til nýjan þráð. (Net köll mega ekki vera á main þráð.)


            @Override
            public void run() { //Run aðferð sem keyrð er fyrir þráð t.
                Project project = new Project();  //Setja upp gögnin sem þarf að vinna úr.
                project.setToken(inputToken);


                final ResponseWrapper<Project> res = parser.login(project);  //Kall á DataParser til að vinna úr gögnum

                // Setjum verkefnið(e.Project) í ílát (e.ProjectContainer) ef login heppnast.
                if (res.getSuccess()) {
                    Project p = res.getContent();
                    ProjectContainer.setProject(p);
                }

                final LoginActivity activity = (LoginActivity) context;
                activity.runOnUiThread(new Runnable() {
                    /*
                        Hérna erum við að biðja  UserInterfaceThread að taka við aftur. Við þurfum að
                        gera það þegar allt networking er búið og við viljum birta gögnin.
                     */
                    @Override
                    public void run() {  //Implement run aðferð sem main þráður á að keyra
                        activity.afterLogin(res.getSuccess());
                    }
                });
            }
        });

        t.start(); //Keyrum þráðinn.
    }

    /**
     * Býr til nýtt async kall sem býr til project á server.
     * @param name nafn á project
     * @param token token á project.
     */
    public void create(String name, String token) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                Project project = new Project();
                project.setName(name);
                project.setToken(token);

                final ResponseWrapper<Project> res = parser.create(project);

                Project projectResponse = null;
                if (res.getSuccess()) {
                    projectResponse = res.getContent();
                    Log.e("TEST", projectResponse.toString());
                }

                final Project returnValue = projectResponse;
                final CreateProjectActivity activity = (CreateProjectActivity) context;
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.projectCreated(returnValue);
                    }
                });
            }
        });

        t.start();
    }

    /**
     * Býr til nýtt async kall á server til að tékka hvort notandi sé innskráður.
     */
    public void checkLogin() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                final ResponseWrapper<Project> res = parser.checkLogin();

                if (res.getSuccess()) {
                    ProjectContainer.setProject(res.getContent());
                }

                final LoginActivity activity = (LoginActivity) context;
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.isLoggedIn(res.getSuccess());
                    }
                });
            }
        });

        t.start();
    }

    public void logout() {
        Networking.clearCookies();
    }
}
