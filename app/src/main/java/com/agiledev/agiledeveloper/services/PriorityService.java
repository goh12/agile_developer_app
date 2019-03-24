package com.agiledev.agiledeveloper.services;

import android.content.Context;
import android.widget.ArrayAdapter;

//import com.agiledev.agiledeveloper.PriorityActivity;
import com.agiledev.agiledeveloper.PriorityActivity;
import com.agiledev.agiledeveloper.ProjectActivity;
import com.agiledev.agiledeveloper.UserStoryDisplayActivity;
import com.agiledev.agiledeveloper.UserStoryEditActivity;
import com.agiledev.agiledeveloper.dataparsers.PriorityDataParser;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.entities.Estimate;
import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.entities.UserStory;
import com.agiledev.agiledeveloper.utils.ProjectContainer;

import java.util.List;

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
    public void create(ArrayAdapter adapter, UserStory us, Estimate priorityEstimate) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<Estimate> res = parser.create(priorityEstimate);
                final PriorityActivity activity = (PriorityActivity) context;

                if (res.getSuccess()) {
                    Estimate es = res.getContent();
                    es.setType(Estimate.Type.PRIORITY);
                    us.getPriorityEstimates().add(es);
                    es.setUserStory(us);
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
     * Eyðir PriorityEstimate
     * @param priorityEstimate
     */
    public void delete(Estimate priorityEstimate, ArrayAdapter adapter) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<Estimate> res = parser.delete(priorityEstimate);
                final PriorityActivity activity = (PriorityActivity) context;

                if (res.getSuccess()) {
                    UserStory us = priorityEstimate.getUserStory();
                    List<Estimate> estimates = us.getPriorityEstimates();

                    for (int i = 0; i < estimates.size(); i++) {
                        Estimate es = estimates.get(i);
                        if (priorityEstimate.getId() == es.getId()) {
                            estimates.remove(i);

                        }
                    }

                }

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (res.getSuccess()) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });

        t.start();
    }

}
