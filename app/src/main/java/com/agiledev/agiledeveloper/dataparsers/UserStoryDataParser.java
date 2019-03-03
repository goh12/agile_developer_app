package com.agiledev.agiledeveloper.dataparsers;
import android.util.Log;

import com.agiledev.agiledeveloper.datacontrollers.*;
import com.agiledev.agiledeveloper.entities.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

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
            Log.e("TRASG", userStoryJSON.toString());
            UserStory us = new UserStory(userStoryJSON.getLong("id"));
            us.setAuthor(userStoryJSON.getString("author"));
            us.setTextContent(userStoryJSON.getString("textContent"));

            us.setPriority(userStoryJSON.getInt("priority"));
            us.setPlanningPokerPriority(userStoryJSON.getInt("planningPokerPriority"));
            us.setCreated(dateFormat.parse(userStoryJSON.getString("created")));

            // Create JSON array for each estimate array
            JSONArray priorityEstimates = userStoryJSON.getJSONArray("priorityEstimates");
            JSONArray planningPokerPriority = userStoryJSON.getJSONArray("planningPokerEstimates");

            // Create empty arrays for storing estimates
            PriorityEstimate[] arrayEstimates = new PriorityEstimate[priorityEstimates.length()];
            PlanningPokerEstimate[] arrayPlanningPokerEsitmates =
                    new PlanningPokerEstimate[planningPokerPriority.length()];

            // Populate priority estimates array
            for(int i = 0; i<priorityEstimates.length(); i++){
                JSONObject priorityEsimate = priorityEstimates.getJSONObject(i);
                PriorityEstimate estimate =
                        new PriorityEstimate(priorityEsimate.getInt("id"));
                estimate.setEstimate(priorityEsimate.getInt("estimate"));
                estimate.setExplanation(priorityEsimate.getString("explanation"));
                arrayEstimates[i] = estimate;
            }

            // Populate PlanningPokerEstimates array
            for(int i = 0; i<planningPokerPriority.length(); i++){
                JSONObject planningPokerPriorityEsimate = planningPokerPriority.getJSONObject(i);
                PlanningPokerEstimate estimate =
                        new PlanningPokerEstimate(planningPokerPriorityEsimate.getInt("id"));
                estimate.setEstimate(planningPokerPriorityEsimate.getInt("estimate"));
                estimate.setExplanation(planningPokerPriorityEsimate.getString("explanation"));
                arrayPlanningPokerEsitmates[i] = estimate;
            }

            // Turn arrays to lists
            List<PriorityEstimate> prioEstimates = Arrays.asList(arrayEstimates);
            List<PlanningPokerEstimate> planningPokerEstimates = Arrays.asList();

            // put lists into UserStory
            us.setPlanningPokerEstimates(planningPokerEstimates);
            us.setPriorityEstimates(prioEstimates);
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

