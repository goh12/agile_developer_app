package com.agiledev.agiledeveloper.utils;

import android.widget.ArrayAdapter;

import com.agiledev.agiledeveloper.entities.Project;
import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProjectContainer {
    private static List<UserStory> userStories;
    private static List<UserStory> masterList;
    private static Project project;

    private static boolean orderByPriority = false;

    public static void  setProject(Project p){
        project = p;
    }

    public static Project getProject() {
        return project;
    }

    public static void setUserStories(List<UserStory> stories) {
        masterList = stories;


        userStories = new ArrayList<>(masterList);

        if (orderByPriority) {
            sortByPriority();
        }
    }

    public static List<UserStory> getUserStories(){
        if (userStories == null) {
            userStories = new ArrayList<UserStory>();
        }
        return userStories;
    }


    public static void sortUserstoriesByPriority(boolean bool) {
        orderByPriority = bool;

        if (bool) {
            sortByPriority();
        } else {
            userStories = new ArrayList<>(masterList);
        }
    }

    private static void sortByPriority() {
        Collections.sort(userStories, new Comparator<UserStory>() {
            @Override
            public int compare(UserStory o1, UserStory o2) {
                int p1 = o1.getPriority();
                int p2 = o2.getPriority();
                if (p1 > p2) return 1;
                else if (p1 < p2) return -1;
                return 0;
            }
        });

        Collections.reverse(userStories);
    }
}

