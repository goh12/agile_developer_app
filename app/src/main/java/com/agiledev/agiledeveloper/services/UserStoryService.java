package com.agiledev.agiledeveloper.services;


import android.content.Context;
import android.util.Log;

import com.agiledev.agiledeveloper.ProjectActivity;
import com.agiledev.agiledeveloper.UserStoryDisplayActivity;
import com.agiledev.agiledeveloper.UserStoryEditActivity;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.dataparsers.UserStoryDataParser;
import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.List;

public class UserStoryService {

    Context context;
    UserStoryDataParser parser;

    /**
     * Constructor
     * @param context Activity notar þetta service.
     */
    public UserStoryService(Context context) {
        this.context = context;
        this.parser = new UserStoryDataParser();
    }

    /**
     * Býr til nýtt user story.
     * @param userStory
     */
    public void create(UserStory userStory) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<UserStory> res = parser.create(userStory);

                final UserStoryEditActivity activity = (UserStoryEditActivity) context;
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.userStoryCreated(res.getSuccess(), res.getMessage());
                    }
                });
            }
        });

        t.start();
    }

    /**
     * Eyðir User story
     * @param userStory
     */
    public void delete(UserStory userStory) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<UserStory> res = parser.delete(userStory);
                final UserStoryDisplayActivity activity = (UserStoryDisplayActivity) context;

                if (res.getSuccess()) {
                    Log.e("TEST", res.getMessage());
                }

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.userStoryDeleted(res.getSuccess(), res.getMessage());
                    }
                });
            }
        });

        t.start();
    }

    /**
     * Uppfærir userstory
     * @param userStory
     */
    public void update(UserStory userStory) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<UserStory> res = parser.update(userStory);

                UserStory UserStoryResponse = null;
                if (res.getSuccess()) {
                    UserStoryResponse = res.getContent();
                    Log.e("TEST", UserStoryResponse.toString());
                }

                final UserStory returnValue = UserStoryResponse;
                final UserStoryEditActivity activity = (UserStoryEditActivity) context;
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.userStoryUpdated(res.getSuccess(), res.getMessage());
                    }
                });
            }
        });

        t.start();
    }

    /**
     * Nær í allar user stories fyrir project.
     */
    public void getAll() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper<List<UserStory>> res = parser.getAll();
                final ProjectActivity activity = (ProjectActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (res.getSuccess()) {
                            activity.displayUserStories(res.getContent());
                        } else {
                            activity.displayUserStories(null);
                        }

                    }
                });
            }
        });

        t.start();
    }

}
