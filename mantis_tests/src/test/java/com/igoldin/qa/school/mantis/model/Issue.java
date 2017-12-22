package com.igoldin.qa.school.mantis.model;

import java.util.Objects;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private ProjectData project;
    private String category;
    private String status;
    private String resolution;

    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getResolution() {
        return resolution;
    }

    public Issue withResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Issue withCategory(String category) {
        this.category = category;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectData getProject() {
        return project;
    }

    public Issue withProject(ProjectData project) {
        this.project = project;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(summary, issue.summary) &&
                Objects.equals(description, issue.description) &&
                Objects.equals(project, issue.project) &&
                Objects.equals(category, issue.category) &&
                Objects.equals(status, issue.status) &&
                Objects.equals(resolution, issue.resolution);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, summary, description, project, category, status, resolution);
    }
}
