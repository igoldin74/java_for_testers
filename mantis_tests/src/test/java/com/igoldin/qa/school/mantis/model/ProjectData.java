package com.igoldin.qa.school.mantis.model;

public class ProjectData {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public ProjectData withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ProjectData withName(String name) {
        this.name = name;
        return this;
    }
}
