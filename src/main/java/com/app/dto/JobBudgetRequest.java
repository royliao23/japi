package com.app.dto;

public class JobBudgetRequest {
    private Long jobId;
    private Long projectId;
    private Double budget;
    private String note;

    // Constructors
    public JobBudgetRequest() {}

    public JobBudgetRequest(Long jobId, Long projectId, Double budget, String note) {
        this.jobId = jobId;
        this.projectId = projectId;
        this.budget = budget;
        this.note = note;
    }

    // Getters and Setters
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
