package com.agiledev.agiledeveloper.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserStory implements Serializable {
    private long id;

    private String author;
    private String textContent;

    private int priority;
    private int planningPokerPriority;


    private List<Estimate> priorityEstimates;
    private List<Estimate> planningPokerEstimates;

    private Date created;

    public UserStory() {}
    public UserStory(long id) {
        this.id = id;
    }

    public long getId() { return id; }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<Estimate> getPriorityEstimates() {
        return priorityEstimates;
    }

    public void setPriorityEstimates(List<Estimate> priorityEstimates) {
        this.priorityEstimates = priorityEstimates;
    }

    public List<Estimate> getPlanningPokerEstimates() {
        return planningPokerEstimates;
    }

    public void setPlanningPokerEstimates(List<Estimate> planningPokerEstimates) {
        this.planningPokerEstimates = planningPokerEstimates;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getPriority() { return priority; }

    public void setPriority(int priority) { this.priority = priority; }

    public int getPlanningPokerPriority() { return planningPokerPriority; }

    public void setPlanningPokerPriority(int planningPokerPriority) {
        this.planningPokerPriority = planningPokerPriority;
    }

    public long getProjectId() {
        return this.id;
    }
}
