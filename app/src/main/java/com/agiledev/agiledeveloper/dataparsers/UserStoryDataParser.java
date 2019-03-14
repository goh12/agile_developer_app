package com.agiledev.agiledeveloper.dataparsers;

import com.agiledev.agiledeveloper.datacontrollers.*;
import com.agiledev.agiledeveloper.entities.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserStoryDataParser {
    UserStoryDataController controller;

    public UserStoryDataParser() {
        this.controller = new UserStoryDataController();
    }

    public ResponseWrapper create(UserStory userStory) {
        try {

            JSONObject ob = new JSONObject();
            ob.put("textContent", userStory.getTextContent());
            ob.put("author", userStory.getAuthor());

            JSONObject response =  this.controller.create(ob);

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            // þurfum ekki að búa til UserStory hlut ef request-ið gekk ekki
            if (!success) {
                return new ResponseWrapper(success, message, null);
            }
            //boolean success, String message, Object content
            return new ResponseWrapper(success, message, userStory);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public ResponseWrapper update(UserStory userStory) {
        try {

            JSONObject ob = UStoJSON(userStory);
            JSONObject response =  this.controller.update(ob);

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            // þurfum ekki að búa til UserStory hlut ef request-ið gekk ekki
            if (!success) {
                return new ResponseWrapper(success, message, null);
            }
            //boolean success, String message, Object content
            return new ResponseWrapper(success, message, userStory);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public ResponseWrapper delete(UserStory userStory){
        try {

            JSONObject ob = UStoJSON(userStory);
            JSONObject response =  this.controller.delete(ob);

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            // þurfum ekki að búa til UserStory hlut ef request-ið gekk ekki
            if (!success) {
                return new ResponseWrapper(success, message, null);
            }
            //boolean success, String message, Object content
            return new ResponseWrapper(success, message, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public ResponseWrapper getAll(){
        try{
            JSONObject response =  this.controller.getAll();

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            if (!success) {
                return new ResponseWrapper(success, message, null);
            }

            JSONArray content = response.getJSONArray("content");

            ArrayList<UserStory> userStories = new ArrayList<>();
            for(int i = 0; i<content.length(); i++){
                userStories.add(JSONtoUS(content.getJSONObject(i)));
            }

            return new ResponseWrapper(true, "Success", userStories);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ResponseWrapper(false, "Fetching user stories failed", null);
    }

    private UserStory JSONtoUS(JSONObject userStoryJSON){
        try{

            //Format: 2019-02-21T16:51:04.689+0000
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            // Set variables
            UserStory us = new UserStory(userStoryJSON.getLong("id"));
            us.setAuthor(userStoryJSON.getString("author"));
            us.setTextContent(userStoryJSON.getString("textContent"));

            us.setPriority(userStoryJSON.getInt("priority"));
            us.setPlanningPokerPriority(userStoryJSON.getInt("planningPokerPriority"));
            us.setCreated(dateFormat.parse(userStoryJSON.getString("created")));

            // Create JSON array for each estimate array
            JSONArray priorityEstimates = userStoryJSON.getJSONArray("priorityEstimates");
            JSONArray planningPokerPriority = userStoryJSON.getJSONArray("planningPokerEstimates");


            // Populate priority estimates array
            ArrayList<Estimate> priorityEstimatesList = new ArrayList<>();
            for(int i = 0; i<priorityEstimates.length(); i++){
                JSONObject priorityEsimate = priorityEstimates.getJSONObject(i);
                Estimate estimate =
                        new Estimate(priorityEsimate.getInt("id"));
                estimate.setEstimate(priorityEsimate.getInt("estimate"));
                estimate.setExplanation(priorityEsimate.getString("explanation"));
                priorityEstimatesList.add(estimate);
            }

            // Populate PlanningPokerEstimates array
            ArrayList<Estimate> planningPokerEstimatesList = new ArrayList<>();
            for(int i = 0; i<planningPokerPriority.length(); i++){

                JSONObject planningPokerPriorityEsimate = planningPokerPriority.getJSONObject(i);
                Estimate estimate =
                        new Estimate(planningPokerPriorityEsimate.getInt("id"));
                estimate.setEstimate(planningPokerPriorityEsimate.getInt("estimate"));
                estimate.setExplanation(planningPokerPriorityEsimate.getString("explanation"));
                planningPokerEstimatesList.add(estimate);
            }

            // put lists into UserStory
            us.setPlanningPokerEstimates(planningPokerEstimatesList);
            us.setPriorityEstimates(priorityEstimatesList);
            return us;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;

    }

    private JSONObject UStoJSON(UserStory userStory) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            JSONObject ob = new JSONObject();

            ob.put("id", userStory.getId());
            ob.put("author", userStory.getAuthor());
            ob.put("textContent", userStory.getTextContent());
            ob.put("projectId", userStory.getProjectId());
            ob.put("priority", userStory.getPriority());
            ob.put("planningPokerPriority", userStory.getPlanningPokerPriority());
            ob.put("created", dateFormat.format(userStory.getCreated()));

            return ob;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}

