package com.agiledev.agiledeveloper.services;

import android.content.Context;

//import com.agiledev.agiledeveloper.PriorityActivity;
import com.agiledev.agiledeveloper.PriorityActivity;
import com.agiledev.agiledeveloper.ProjectActivity;
import com.agiledev.agiledeveloper.UserStoryDisplayActivity;
import com.agiledev.agiledeveloper.UserStoryEditActivity;
import com.agiledev.agiledeveloper.dataparsers.PriorityDataParser;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.entities.Estimate;

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
    public void create(Estimate priorityEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<Estimate> res = parser.create(priorityEstimate);
                final PriorityActivity activity = (PriorityActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.priorityEstimateCreated(res.getSuccess(), res.getMessage());
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
    public void delete(Estimate priorityEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<Estimate> res = parser.delete(priorityEstimate);
                final ProjectActivity activity = (ProjectActivity) context;

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
