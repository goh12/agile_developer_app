package com.agiledev.agiledeveloper.dataparsers;

import com.agiledev.agiledeveloper.datacontrollers.PlanningPokerDataController;
import com.agiledev.agiledeveloper.entities.Estimate;
import com.agiledev.agiledeveloper.entities.Project;

import org.json.JSONException;
import org.json.JSONObject;

public class PlanningPokerDataParser {

    private PlanningPokerDataController controller;

    public PlanningPokerDataParser() {
        this.controller = new PlanningPokerDataController();
    }

    public ResponseWrapper<Estimate> create(Estimate estimate) {
        try {
            JSONObject ob = new JSONObject();
            /*{
                "id": 4,
                "member": null, // HUNSA
                "estimate": 0.5,
                "explanation": "Trash Boii"
            }*/
            ob.put("estimate", estimate.getEstimate());
            ob.put("explanation", estimate.getExplanation());
            JSONObject obUserStory = new JSONObject();
            obUserStory.put("id", estimate.getUserStory().getId());
            ob.putOpt("userStory", obUserStory);
            ob = this.controller.create(ob);

            boolean success = ob.getBoolean("success");
            String message = ob.getString("message");

            if (!success) {
                return new ResponseWrapper<Estimate>(success, message, null);
            } else {
                JSONObject projectJSON = ob.getJSONObject("content");
                int returnEstimate = projectJSON.getInt("estimate");
                String explanation = projectJSON.getString("explanation");
                int id = projectJSON.getInt("id");

                Estimate planningPokerEstimate = new Estimate(id);
                planningPokerEstimate.setExplanation(explanation);
                planningPokerEstimate.setEstimate(returnEstimate);

                return new ResponseWrapper<>(success, message, planningPokerEstimate);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseWrapper<Estimate> delete(Estimate estimate) {
        try {
            JSONObject ob = new JSONObject();
            ob.put("id",estimate.getId());
            JSONObject obUserStory = new JSONObject();
            obUserStory.put("id", estimate.getUserStory().getId());
            ob.putOpt("userStory", obUserStory);
            JSONObject response = this.controller.delete(ob);

            boolean success = response.getBoolean("success");
            String message = response.getString("message");

            return new ResponseWrapper<>(success, message, null);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }


    public ResponseWrapper<Project> finalizeEstimates() {
        try {
            JSONObject res = this.controller.finalizeEstimate(new JSONObject());

            boolean success = res.getBoolean("success");
            String message = res.getString("message");

            return new ResponseWrapper<>(success, message, null);
        } catch (JSONException e) {
            return new ResponseWrapper<>(false, "Server error", null);
        }
    }

}
