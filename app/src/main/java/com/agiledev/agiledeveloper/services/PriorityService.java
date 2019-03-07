package com.agiledev.agiledeveloper.services;

import android.content.Context;

import com.agiledev.agiledeveloper.dataparsers.PriorityDataParser;
import com.agiledev.agiledeveloper.entities.PriorityEstimate;

public class PriorityService {

    Context context;
    PriorityDataParser parser;

    /**
     * Constructor
     * @param context Activity notar þetta service.
     */
    public PriorityService(Context context) {
        this.context = context;
        this.parser = new PriorityDataParser();
    }

    /**
     * Vistar PriorityEstimate
     * @param priorityEstimate
     */
    public void create(PriorityEstimate priorityEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                // final ResponseWrapper res = parser.create(priorityEstimate);
                final PriorityActivity activity = (PriorityActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       // activity.priorityEstimateSaved(res.getSuccess(), res.getMessage());
                    }
                });
            }
        });

        t.start();
    }


    /**
     * Eyðir PriorityEstimate
     * @param priorityEstimate
     */
    public void delete(PriorityEstimate priorityEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                // final ResponseWrapper res = parser.delete(priorityEstimate);
                final PriorityActivity activity = (PriorityActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       // activity.priorityDeleted(res.getSuccess(), res.getMessage());
                    }
                });
            }
        });

        t.start();
    }

}
