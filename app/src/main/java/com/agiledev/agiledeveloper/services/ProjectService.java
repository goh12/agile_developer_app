package com.agiledev.agiledeveloper.services;

import android.content.Context;
import android.util.Log;

import com.agiledev.agiledeveloper.MainActivity;
import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.datacontrollers.networking.Networking;
import com.agiledev.agiledeveloper.dataparers.ProjectDataParser;
import com.agiledev.agiledeveloper.entities.Project;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectService {

    Context context;
    ProjectDataParser parser;

    public ProjectService(Context context) {
        this.context = context;
        this.parser = new ProjectDataParser();
    }


    public void login() { //Prufu og sýnifall
        Thread t = new Thread(new Runnable() { //Búa til nýjan þráð. (Net köll mega ekki vera á main þráð.)
            /*
                Það er bannað að gera networking á UserInterfaceThread í android appinu. Þessvegna þurfum
                við að búa til nýjan þráð og keyra hann. Síðan í lokin á honum þá biðjum við
                UserInterficeThread um að birta niðurstöðurnar.
             */
            @Override
            public void run() { //Run aðferð sem keyrð er fyrir þráð t.
                Project project = new Project();  //Setja upp gögnin sem þarf að vinna úr.
                project.setToken("florg");

                String ret = parser.testLogin(project);  //Kall á DataParser til að vinna úr gögnum

                MainActivity activity = (MainActivity) context;
                activity.runOnUiThread(new Runnable() {
                    /*
                        Hérna erum við að biðja  UserInterfaceThread að taka við aftur. Við þurfum að
                        gera það þegar allt networking er búið og við viljum birta gögnin.
                     */
                    @Override
                    public void run() {  //Implement run aðferð sem main þráður á að keyra
                        activity.setText(ret);
                    }
                });
            }
        });

        t.start(); //Keyrum þráðinn.
    }

}
