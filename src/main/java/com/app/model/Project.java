package com.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Project Model that represents a project entity in the database.
 * This class corresponds to the 'Project' Pydantic model in the FastAPI code.
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id")
    private Long code;

    @NotBlank(message = "Project name is mandatory")
    @Size(max = 255)
    @Column(name = "project_name")
    private String projectName;

    @NotBlank(message = "Description is mandatory")
    @Lob // For larger text fields
    private String description;

    @NotBlank(message = "Status is mandatory")
    @Size(max = 50)
    private String status;

    @NotBlank(message = "Manager is mandatory")
    @Size(max = 255)
    private String manager;

    public Project() {
    }

    public Project(Long code, String projectName, String description, String status, String manager) {
        this.code = code;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.manager = manager;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(code, project.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
