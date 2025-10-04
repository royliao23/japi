package com.app.dto;

public class JobBudgetResponse {
    private Long code;
    private Long jobId;
    private Long projectId;
    private Double budget;
    private String note;
    private JobResponse job;
    private ProjectResponse project;

    // Constructors
    public JobBudgetResponse() {}

    public JobBudgetResponse(Long code, Long jobId, Long projectId, Double budget, 
                           String note, JobResponse job, ProjectResponse project) {
        this.code = code;
        this.jobId = jobId;
        this.projectId = projectId;
        this.budget = budget;
        this.note = note;
        this.job = job;
        this.project = project;
    }

    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public JobResponse getJob() { return job; }
    public void setJob(JobResponse job) { this.job = job; }

    public ProjectResponse getProject() { return project; }
    public void setProject(ProjectResponse project) { this.project = project; }
}
