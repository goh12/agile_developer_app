package com.agiledev.agiledeveloper.services;


import android.content.Context;
import android.util.Log;

import com.agiledev.agiledeveloper.CreateProjectActivity;
import com.agiledev.agiledeveloper.LoginActivity;
import com.agiledev.agiledeveloper.UserStoryDisplayActivity;
import com.agiledev.agiledeveloper.dataparsers.ProjectDataParser;
import com.agiledev.agiledeveloper.dataparsers.ResponseWrapper;
import com.agiledev.agiledeveloper.dataparsers.UserStoryDataParser;
import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.entities.UserStory;

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

    public void create(UserStory userStory) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper res = parser.create(userStory);
                UserStory UserStoryResponse = null;

                if (res.getSuccess()) {
                    UserStoryResponse = (UserStory) res.getContent();
                    Log.e("TEST", UserStoryResponse.toString());
                }

                final UserStory returnValue = UserStoryResponse;
                final UserStoryDisplayActivity activity = (UserStoryDisplayActivity) context;
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //activity.afterSaving(returnValue);
                    }
                });
            }
        });

        t.start();
    }

    public void delete(UserStory userStory) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper res = parser.delete(userStory);
                final UserStoryDisplayActivity activity = (UserStoryDisplayActivity) context;

                if (res.getSuccess()) {
                    Log.e("TEST", res.getMessage());
                }

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        //activity.afterDeleting(res.getSuccess());
                    }
                });
            }
        });

        t.start();
    }

    public void uppdate(UserStory userStory) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper res = parser.update(userStory);

                UserStory UserStoryResponse = null;
                if (res.getSuccess()) {
                    UserStoryResponse = (UserStory) res.getContent();
                    Log.e("TEST", UserStoryResponse.toString());
                }

                final UserStory returnValue = UserStoryResponse;
                final UserStoryDisplayActivity activity = (UserStoryDisplayActivity) context;
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //activity.afterSaving(res.getSuccess());
                    }
                });
            }
        });

        t.start();
    }

    public void getAll() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                final ResponseWrapper res = parser.getAll();
                final UserStoryDisplayActivity activity = (UserStoryDisplayActivity) context;

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //activity.afterReturningAll(res.getContent());
                    }
                });
            }
        });

        t.start();
    }

}
