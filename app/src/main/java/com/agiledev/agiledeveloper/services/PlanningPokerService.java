package com.agiledev.agiledeveloper.services;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.agiledev.agiledeveloper.PlanningPokerActivity;
import com.agiledev.agiledeveloper.dataparsers.PlanningPokerDataParser;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.entities.Estimate;
import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.List;


public class PlanningPokerService {

    Context context;
    PlanningPokerDataParser parser;

    /**
     * Constructor
     * @param context Activity notar þetta service.
     */
    public PlanningPokerService(Context context) {
        this.context = context;
        this.parser = new PlanningPokerDataParser();
    }

    /**
     * Vistar PlanningPokerEstimate
     * @param planningPokerEstimate
     */
    public void create(ArrayAdapter adapter, UserStory story, Estimate planningPokerEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<Estimate> res = parser.create(planningPokerEstimate);
                final PlanningPokerActivity activity = (PlanningPokerActivity) context;

                if (res.getSuccess()) {
                    Estimate es = res.getContent();
                    es.setType(Estimate.Type.PLANNING_POKER);
                    story.getPlanningPokerEstimates().add(es);
                    es.setUserStory(story);
                }
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        t.start();
    }


    /**
     * Eyðir PlanningPokerEstimate
     * @param planningPokerEstimate
     */
    public void delete(Estimate planningPokerEstimate, ArrayAdapter adapter) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<Estimate> res = parser.delete(planningPokerEstimate);
                final PlanningPokerActivity activity = (PlanningPokerActivity) context;

                if (res.getSuccess()) {
                    UserStory us = planningPokerEstimate.getUserStory();
                    List<Estimate> estimates = us.getPlanningPokerEstimates();

                    for (int i = 0; i < estimates.size(); i++) {
                        Estimate es = estimates.get(i);
                        if (planningPokerEstimate.getId() == es.getId()) {
                            estimates.remove(i);
                        }
                    }
                }

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        t.start();
    }



}
