package com.agiledev.agiledeveloper.entities;

import java.io.Serializable;


public class Estimate implements Serializable {
    public enum Type {
        PLANNING_POKER, PRIORITY
    }

    private int id;
    private int estimate;
    private String explanation;
    private UserStory userStory;

    private Type type;

    public Estimate(int id) {
        this.id = id;
    }

    public Estimate(int estimate, String explanation, UserStory userStory) {
        this.estimate = estimate;
        this.explanation = explanation;
        this.userStory = userStory;
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
