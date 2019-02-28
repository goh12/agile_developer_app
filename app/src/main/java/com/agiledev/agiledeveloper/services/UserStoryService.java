package com.agiledev.agiledeveloper.services;

import com.agiledev.agiledeveloper.datacontrollers.UserStoryDataController;
import com.agiledev.agiledeveloper.entities.UserStory;

import java.util.ArrayList;
import java.util.List;

public class UserStoryService {

    public List<UserStory> getAll() {

        ArrayList<UserStory> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ls.add(new UserStory());
        }

        return ls;
    }
}
