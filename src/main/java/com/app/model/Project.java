package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents the Project entity, which maps to the 'projects' table in the database.
 * This class corresponds to the 'Project' Pydantic model in the FastAPI code.
 * Replaced Lombok annotations with explicit getters and setters.
 */
@Entity
public class Project {

    /**
     * The unique identifier for the project. This is the primary key.
     * Corresponds to the 'code' field in the FastAPI model.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the project. Cannot be blank.
     */
    @NotBlank(message = "Project name cannot be blank")
    @Column(name = "project_name")
    private String projectName;

    /**
     * A description of the project.
     */
    private String description;

    /**
     * The current status of the project.
     */
    private String status;

    /**
     * The manager assigned to the project.
     */
    private String manager;

    // Default constructor is required by JPA
    public Project() {
    }

    public Project(Long id, String projectName, String description, String status, String manager) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * The 'code' property is added to match the FastAPI response format for creation.
     * It's a derived property that returns the entity's ID.
     */
    public Long getCode() {
        return this.id;
    }
}
