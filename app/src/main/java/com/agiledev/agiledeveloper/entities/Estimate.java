package com.agiledev.agiledeveloper.entities;

import java.io.Serializable;


public class Estimate implements Serializable {
    public enum Type {
        PLANNING_POKER, PRIORITY
    }

    private final int id;
    private int estimate;
    private String explanation;
    private UserStory userStory;

    private Type type;

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


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
