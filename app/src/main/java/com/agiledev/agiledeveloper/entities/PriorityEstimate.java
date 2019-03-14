package com.agiledev.agiledeveloper.entities;

import java.io.Serializable;

public class PriorityEstimate implements Serializable {


    private final int id;
    private int estimate;
    private String explanation;
    private UserStory userStory;


    public PriorityEstimate(int id) {
        this.id = id;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public String getExplanation() {
        return explanation;
    }
    public int getId() {return id;}
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public UserStory getUserStory(){
        return this.userStory;
    }
    public void setUserStory(Long id){
        UserStory userStory = new UserStory(id);
        this.userStory = userStory;
    }
}
