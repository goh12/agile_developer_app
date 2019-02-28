package com.agiledev.agiledeveloper.entities;

import java.util.Date;
import java.util.List;

public class UserStory {
    private String author;
    private String textContent;

    private List<PriorityEstimate> priorityEstimates;
    private List<PlanningPokerEstimate> planningPokerEstimates;

    private Date created;


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

    public List<PriorityEstimate> getPriorityEstimates() {
        return priorityEstimates;
    }

    public void setPriorityEstimates(List<PriorityEstimate> priorityEstimates) {
        this.priorityEstimates = priorityEstimates;
    }

    public List<PlanningPokerEstimate> getPlanningPokerEstimates() {
        return planningPokerEstimates;
    }

    public void setPlanningPokerEstimates(List<PlanningPokerEstimate> planningPokerEstimates) {
        this.planningPokerEstimates = planningPokerEstimates;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
