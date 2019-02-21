package com.agiledev.agiledeveloper.services;

import android.content.Context;
import android.util.Log;

import com.agiledev.agiledeveloper.CreateProjectActivity;
import com.agiledev.agiledeveloper.LoginActivity;
import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
import com.agiledev.agiledeveloper.dataparsers.ProjectDataParser;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.entities.Project;

public class ProjectService {

    Context context;
    ProjectDataParser parser;
    private boolean logInStatus;
    private boolean saveStatus;
    private boolean loggedIn;

    public ProjectService(Context context) {
        this.context = context;
        this.parser = new ProjectDataParser();
    }


    public boolean login(String inputToken) { //Prufu og sýnifall
        Thread t = new Thread(new Runnable() { //Búa til nýjan þráð. (Net köll mega ekki vera á main þráð.)
            /*
                Það er bannað að gera networking á UserInterfaceThread í android appinu. Þessvegna þurfum
                við að búa til nýjan þráð og keyra hann. Síðan í lokin á honum þá biðjum við
                UserInterficeThread um að birta niðurstöðurnar.
             */
            @Override
            public void run() { //Run aðferð sem keyrð er fyrir þráð t.
                Project project = new Project();  //Setja upp gögnin sem þarf að vinna úr.
                project.setToken(inputToken);

                final ResponseWrapper res = parser.login(project);  //Kall á DataParser til að vinna úr gögnum

                if(res.getSuccess()) {
                    logInStatus = true;
                } else {logInStatus = false;}

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
        return logInStatus;
    }

    public boolean save(String name, String token) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                Project project = new Project();
                project.setName(name);
                project.setToken(token);

                final ResponseWrapper res = parser.save(project);

                Project projectResponse = null;
                if (res.getSuccess()) {
                    projectResponse = (Project) res.getContent();
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
        return saveStatus;
    }


    public void checkLogin() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                final ResponseWrapper res = parser.checkLogin();

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
