package com.app.dto;

import java.time.LocalDate;

public class JobbyResponse {
    private Long code;
    private LocalDate dueAt;
    private Double cost;
    private String status;
    private Long byId;
    private Long projectId;
    private Long jobId;
    
    // Constructors
    public JobbyResponse() {}
    
    public JobbyResponse(Long code, LocalDate dueAt, Double cost, String status, 
                        Long byId, Long projectId, Long jobId) {
        this.code = code;
        this.dueAt = dueAt;
        this.cost = cost;
        this.status = status;
        this.byId = byId;
        this.projectId = projectId;
        this.jobId = jobId;
    }
    
    // Getters and setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    
    public LocalDate getDueAt() { return dueAt; }
    public void setDueAt(LocalDate dueAt) { this.dueAt = dueAt; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Long getById() { return byId; }
    public void setById(Long byId) { this.byId = byId; }
    
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
}
