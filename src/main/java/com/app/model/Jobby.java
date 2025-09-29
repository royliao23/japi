package com.app.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobby")
public class Jobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;

    @JsonProperty("po_id")
    @Column(name = "po_id")
    private Long poId;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "by_id")
    private Long byId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "ref")
    private String ref;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "due_at")
    private LocalDateTime dueAt;

    @Column(name = "status")
    private String status;

    // DEFAULT CONSTRUCTOR - REQUIRED
    public Jobby() {}

    // REMOVE ANY METHOD THAT LOOKS LIKE THIS:
    // public Invoice orElse(Object object) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

    // GETTERS AND SETTERS ONLY - NO OTHER METHODS
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getById() {
        return byId;
    }

    public void setById(Long byId) {
        this.byId = byId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // DO NOT ADD ANY OTHER METHODS BESIDES GETTERS/SETTERS
}