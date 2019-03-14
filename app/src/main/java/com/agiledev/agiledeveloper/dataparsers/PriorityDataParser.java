package com.agiledev.agiledeveloper.dataparsers;

import com.agiledev.agiledeveloper.datacontrollers.PriorityDataController;
import com.agiledev.agiledeveloper.entities.PriorityEstimate;
import com.agiledev.agiledeveloper.entities.Project;

import org.json.JSONException;
import org.json.JSONObject;

public class PriorityDataParser {
    private PriorityDataController controller;

    public PriorityDataParser() {
        this.controller = new PriorityDataController();
    }

    public ResponseWrapper create(PriorityEstimate estimate) {
        try {
            JSONObject ob = new JSONObject();
            //    private final int id;
            //    private int estimate;
            //    private String explanation
            ob.put("estimate", estimate.getEstimate());
            ob.put("explanation", estimate.getExplanation());
            JSONObject obUserStory = new JSONObject();
            obUserStory.put("id", estimate.getUserStory().getId());
            ob.putOpt("userStory", obUserStory);
            ob = this.controller.create(ob);
            boolean success = ob.getBoolean("success");
            String message = ob.getString("message");

            if (!success) {
                return new ResponseWrapper(success, message, null);
            } else {
                JSONObject projectJSON = ob.getJSONObject("content");
                // búum til Project er JSON response-i
                int returnEstimate = projectJSON.getInt("estimate");
                String explanation = projectJSON.getString("explanation");
                int id = projectJSON.getInt("id");

                PriorityEstimate priorityEstimate = new PriorityEstimate(id);
                priorityEstimate.setExplanation(explanation);
                priorityEstimate.setEstimate(returnEstimate);

                return new ResponseWrapper(success, message, priorityEstimate);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseWrapper delete(PriorityEstimate estimate) {
        try {
            JSONObject ob = new JSONObject();
            ob.put("estimate", estimate.getEstimate());
            ob.put("explanation", estimate.getExplanation());
            ob.put("id",estimate.getId());

            JSONObject obUserStory = new JSONObject();
            obUserStory.put("id", estimate.getUserStory().getId());
            ob.putOpt("userStory", obUserStory);
            ob = this.controller.create(ob);
            JSONObject response = this.controller.delete(ob);

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            return new ResponseWrapper(success, message, null);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}

