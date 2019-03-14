package com.agiledev.agiledeveloper.services;

import android.content.Context;

import com.agiledev.agiledeveloper.PlanningPokerActivity;
import com.agiledev.agiledeveloper.dataparsers.PlanningPokerDataParser;
import com.agiledev.agiledeveloper.entities.PlanningPokerEstimate;


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
    public void create(PlanningPokerEstimate planningPokerEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                // final ResponseWrapper res = parser.create(planningPokerEstimate);
                final PlanningPokerActivity activity = (PlanningPokerActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // activity.planningPokerEstimateSaved(res.getSuccess(), res.getMessage());
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
    public void delete(PlanningPokerEstimate planningPokerEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                // final ResponseWrapper res = parser.delete(planningPokerEstimate);
                final PlanningPokerActivity activity = (PlanningPokerActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // activity.planningPokerEstimateDeleted(res.getSuccess(), res.getMessage());
                    }
                });
            }
        });

        t.start();
    }



}
