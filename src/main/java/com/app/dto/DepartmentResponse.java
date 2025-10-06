package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentResponse {
    private Long id;
    
    @JsonProperty("department_name")
    private String departmentName;
    
    private String description;
    private String manager;

    // Constructors
    public DepartmentResponse() {}

    public DepartmentResponse(Long id, String departmentName, String description, String manager) {
        this.id = id;
        this.departmentName = departmentName;
        this.description = description;
        this.manager = manager;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }
}
