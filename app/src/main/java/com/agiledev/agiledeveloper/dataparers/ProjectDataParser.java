package com.agiledev.agiledeveloper.dataparers;

import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.entities.Project;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectDataParser {
    private ProjectDataController controller;

    public ProjectDataParser() {
        this.controller = new ProjectDataController();
    }

    public String testLogin(Project project) {
        try {
            JSONObject ob = new JSONObject();


            if (project.getToken() != null) {
                ob.put("token", project.getToken());
            } else {
                return "Failure, no token passed";
            }

            ob = this.controller.login(ob);
            if (ob == null) {
                return "Networking failure occurred";
            }
            return ob.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
