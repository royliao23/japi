package com.app.repository;

/**
 * A Data Transfer Object (DTO) for the /high/projects/pjcodeandname endpoint.
 * This is used to return only the 'code' and 'project_name' fields,
 * as requested in the original FastAPI code.
 * Removed Lombok annotations.
 */
public class ProjectCodeNameDto {
    private Long id;
    private String projectName;

    public ProjectCodeNameDto() {
    }

    public ProjectCodeNameDto(Long code, String projectName) {
        this.id = code;
        this.projectName = projectName;
    }

    public Long getCode() {
        return id;
    }

    public void setCode(Long code) {
        this.id = code;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
