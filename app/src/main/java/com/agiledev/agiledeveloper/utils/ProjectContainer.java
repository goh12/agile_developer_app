package com.agiledev.agiledeveloper.utils;

import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.ArrayList;
import java.util.List;

public class ProjectContainer {
    private static List<UserStory> userStories;
    private static Project project;

    public static void  setProject(Project p){
        project = p;
    }

    public static Project getProject() {
        return project;
    }

    public static void setUserStories(List<UserStory> stories) {
        userStories = stories;
    }

    public static List<UserStory> getUserStories(){
        if (userStories == null) {
            userStories = new ArrayList<UserStory>();
        }
        return userStories;
    }

    /**
     * Finnur user story frá ID
     * @param id
     * @return UserStory, null ef hún finnst ekki.
     */
    public static UserStory findUserStoryById(long id) {
        for (UserStory us : userStories) {
            if (us.getId() == id) return us;
        }

        return null;
    }
}

