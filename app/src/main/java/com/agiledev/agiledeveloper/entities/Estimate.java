package com.agiledev.agiledeveloper.entities;

import java.io.Serializable;

public class Estimate implements Serializable {
    private final int id;
    private int estimate;
    private String explanation;
    private UserStory userStory;

    public Estimate(int id) {
        this.id = id;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public int getId() {
        return id;
    }

    public String getExplanation() {
        return explanation;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
