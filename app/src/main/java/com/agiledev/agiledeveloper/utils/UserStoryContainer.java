package com.agiledev.agiledeveloper.utils;

import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.ArrayList;
import java.util.List;

public class UserStoryContainer {
    private static List<UserStory> userStories;

    public static void setUserStories(List<UserStory> stories) {
        userStories = stories;
    }

    public static List<UserStory> getUserStories(){
        if (userStories == null) {
            userStories = new ArrayList<UserStory>();
        }
        return userStories;
    }
}

