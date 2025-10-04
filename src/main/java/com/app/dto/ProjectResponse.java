package com.app.dto;

public class ProjectResponse {
    private Long code;
    private String projectName;

    public ProjectResponse(Long code, String projectName) {
        this.code = code;
        this.projectName = projectName;
    }

    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
}
