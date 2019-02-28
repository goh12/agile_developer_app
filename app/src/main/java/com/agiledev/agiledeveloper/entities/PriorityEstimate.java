package com.agiledev.agiledeveloper.entities;

public class PriorityEstimate {
    private final int id;
    private int estimate;
    private String explanation;

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

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
