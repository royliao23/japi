package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectCodeResponse {
    @JsonProperty("code")
    private Long code;
    
    @JsonProperty("project_name")
    private String projectName;

    // Constructors
    public ProjectCodeResponse() {}

    public ProjectCodeResponse(Long code, String projectName) {
        this.code = code;
        this.projectName = projectName;
    }

    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
}
