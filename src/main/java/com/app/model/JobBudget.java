package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobbudget")
public class JobBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "project_id")
    private Long projectId;

    private Double budget;
    private String note;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project project;

    // Constructors
    public JobBudget() {}

    public JobBudget(Long jobId, Long projectId, Double budget, String note) {
        this.jobId = jobId;
        this.projectId = projectId;
        this.budget = budget;
        this.note = note;
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

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
    

