package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentUpdateRequest {
    @JsonProperty("department_name")
    private String departmentName;
    
    private String description;
    private String manager;

    // Constructors
    public DepartmentUpdateRequest() {}

    public DepartmentUpdateRequest(String departmentName, String description, String manager) {
        this.departmentName = departmentName;
        this.description = description;
        this.manager = manager;
    }

    // Getters and Setters
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    // Helper method to check if any field is provided
    public boolean hasUpdates() {
        return departmentName != null || description != null || manager != null;
    }
}
