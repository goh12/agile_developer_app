package com.agiledev.agiledeveloper.dataparsers;

import com.agiledev.agiledeveloper.datacontrollers.ProjectDataController;
import com.agiledev.agiledeveloper.entities.Project;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectDataParser {
    private ProjectDataController controller;

    public ProjectDataParser() {
        this.controller = new ProjectDataController();
    }

    public ResponseWrapper login(Project project) {
        try {
            JSONObject ob = new JSONObject();

            ob.put("token", project.getToken());

            ob = this.controller.login(ob);

            boolean success = ob.getBoolean("success");
            String message = ob.getString("message");
            JSONObject projectJSON = ob.getJSONObject("content");

            // ef ekki success þurfum við ekki að parse-a project hlutinn
            if (!success) {
                return new ResponseWrapper(success, message, null);
            } else {

                String token = projectJSON.getString("token");
                String name = projectJSON.getString("name");

                Project loggedInProject = new Project();
                loggedInProject.setName(name);
                loggedInProject.setToken(token);

                return new ResponseWrapper(success, message, loggedInProject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String save(Project project){
        try {
            JSONObject ob = new JSONObject();

            if (project.getToken() != null) {
                ob.put("token", project.getToken());
            } else {
                return null;
            }

            if (project.getName() != null) {
                ob.put("name", project.getName());
            } else {
                return null;
            }

            ob = this.controller.save(ob);
            return ob.getString("status");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String checkLogin(){
        try {
            JSONObject ob = new JSONObject();

            ob = this.controller.checkLogin();
            return ob.getString("status");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkLogin(Project project) {
        return "false";
    }
}
