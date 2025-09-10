package com.app.repository;

/**
 * A Data Transfer Object (DTO) for the /high/projects/pjcodeandname endpoint.
 * This is used to return only the 'code' and 'project_name' fields,
 * as requested in the original FastAPI code.
 * Removed Lombok annotations.
 */
public class ProjectCodeNameDto {
    private Long code;
    private String projectName;

    public ProjectCodeNameDto() {
    }

    public ProjectCodeNameDto(Long code, String projectName) {
        this.code = code;
        this.projectName = projectName;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
