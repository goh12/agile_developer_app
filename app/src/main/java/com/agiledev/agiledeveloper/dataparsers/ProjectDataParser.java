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

    /**
     * Sér um að kalla á ProjectDataController sem kallar á api-ið og reynir að logga inn
     * Byggir síðan upp staðlaðan ResponseWrapper til að skila
     * @param project
     * @return
     */
    public ResponseWrapper login(Project project) {
        try {
            JSONObject ob = new JSONObject();

            ob.put("token", project.getToken());

            ob = this.controller.login(ob);

            boolean success = ob.getBoolean("success");
            String message = ob.getString("message");

            // ef ekki success þurfum við ekki að parse-a project hlutinn
            if (!success) {
                return new ResponseWrapper(success, message, null);
            } else {
                JSONObject projectJSON = ob.getJSONObject("content");
                // búum til Project er JSON response-i
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

    /**
     * Sér um að kalla á ProjectDataControllerinn sem reynir að búa til nýtt project.
     * Út frá svarinu er byggt upp staðlaður ResponseWrapper til að skila
     * @param project
     * @return
     */
    public ResponseWrapper create(Project project){
        try {
            JSONObject ob = new JSONObject();

            ob.put("token", project.getToken());
            ob.put("name", project.getName());

            // JSON hlutur fæst úr því að reyna búa til nýtt project
            JSONObject response =  this.controller.create(ob);

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            // þurfum ekki að búa til Project hlut ef request-ið gekk ekki
            if (!success) {
                return new ResponseWrapper(success, message, null);
            }

            // Annars byggjum við upp Project hlut til að skila með í ResponseWrapper-inum
            JSONObject projectJSON = response.getJSONObject("content");

            Project projectResponse = new Project();
            projectResponse.setName(projectJSON.getString("name"));
            projectResponse.setToken(projectJSON.getString("token"));

            return new ResponseWrapper(success, message, projectResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fallið sér um að athuga hvort notandi sé með Cookie sem segir til
     * um hvort hann sé loggaður inn í project.
     * Byggir upp staðlaðan ResponseWrapper hlut til að skila út frá svari ProjectDataController
     * @return
     */
    public ResponseWrapper checkLogin(){
        try {
            JSONObject response = this.controller.checkLogin();

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            JSONObject ob = response.getJSONObject("content");
            Project project = new Project();
            project.setName(ob.getString("name"));

            return new ResponseWrapper(success, message, project);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseWrapper(false, e.getMessage(), null);
        }
    }
}
