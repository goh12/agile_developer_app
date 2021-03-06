package com.agiledev.agiledeveloper.entities;

public class Project {

    private String name;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return "Name: " + this.name + ". Token: " + this.token;
    }
}
