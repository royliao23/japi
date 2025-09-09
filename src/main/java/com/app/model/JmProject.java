package com.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jm_project")
public class JmProject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;
    
    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "manager", length = 50)
    private String manager;
    
    @Column(name = "status", length = 20)
    private String status;
    
    // Constructors
    public JmProject() {}
    
    // Parameterized constructor (without code)
    public JmProject(String projectName, String description, 
                   LocalDate startDate, LocalDate endDate, 
                   String manager, String status) {
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manager = manager;
        this.status = status;
    }
    
    // Getters and Setters
    public Integer getCode() { return code; }
    // No setter for code to prevent manual modification
    
    // Other getters and setters...
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}