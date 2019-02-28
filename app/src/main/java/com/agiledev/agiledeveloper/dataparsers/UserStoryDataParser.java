package com.agiledev.agiledeveloper.dataparsers;
import com.agiledev.agiledeveloper.datacontrollers.*;
import com.agiledev.agiledeveloper.entities.*;
import org.json.JSONException;
import org.json.JSONObject;

public class UserStoryDataParser {
    UserStoryDataController controller;

    public UserStoryDataParser() {
        this.controller = new UserStoryDataController();
    }

    public ResponseWrapper create(UserStory userStory) {
        JSONObject ob = new JSONObject();

        ob.put("id", userStory.getId());
        ob.put("author", userStory.getAtuthor());
        ob.put("textContent", userStory.getTextContent());
        ob.put("projectId", userStory.getProjectId());
        ob.put("priority", userStory.getPriority());
        ob.put("created", userStory.getCreated());

        JSONObject response =  this.controller.create(ob);

        boolean success = response.getBoolean("success");
        String message = response.getString("message");

        // þurfum ekki að búa til UserStory hlut ef request-ið gekk ekki
        if (!success) {
            return new ResponseWrapper(success, message, null);
        }
        JSONObject projectJSON = response.getJSONObject("content");

        Project userStoryResponse = new UserStory();
        userStoryResponse.setId(projectJSON.getString("id"));
        userStoryResponse.setAuthor(projectJSON.getString("author"));
        userStoryResponse.setTextContent(projectJSON.getString("textContent"));
        userStoryResponse.setProjectId(projectJSON.getString("projectId"));
        userStoryResponse.setPriority(projectJSON.getString("priority"));
        userStoryResponse.setCreated(projectJSON.getString("created"));

        //boolean success, String message, Object content
        return new ResponseWrapper(success, message, userStoryResponse);
    }
}

/*
try {
            JSONObject ob = new JSONObject();

            ob.put("token", project.getToken());
            ob.put("name", project.getName());

            // JSON hlutur fæst úr því að reyna búa til nýtt project
            JSONObject response =  this.controller.save(ob);

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
 */