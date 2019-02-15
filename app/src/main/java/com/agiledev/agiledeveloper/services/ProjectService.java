package com.agiledev.agiledeveloper.services;

import android.content.Context;
import android.util.Log;

import com.agiledev.agiledeveloper.MainActivity;
import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectService {

    Context context;
    ProjectDataController projectDataController;

    public ProjectService(Context context) {
        this.context = context;
        this.projectDataController = new ProjectDataController();
    }


    public void login() { //Prufu og sýnifall
        Thread t = new Thread(new Runnable() { //Búa til nýjan þráð. (Net köll mega ekki vera á main þráð.)
            @Override
            public void run() {  //Setja allt sem við viljum gera í aðferðina run() fyrir nýjan Runnable()
                try {
                    JSONObject ob = new JSONObject();
                    ob.put("token", "florg");
                    String s = projectDataController.login(ob);

                    MainActivity activity = (MainActivity) context;

                    activity.runOnUiThread(new Runnable() { //Núna biðja activity um að keyra á main þráð til að breyta UI.
                        @Override
                        public void run() {  //Implement run aðferð sem main þráður á að keyra
                            activity.setText(s);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSON", "Error creating json object");
                }
            }
        });

        t.start(); //Keyrum þráðinn.
    }

}
