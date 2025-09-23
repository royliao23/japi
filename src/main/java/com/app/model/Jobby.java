package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A simple model for the `jobby` table, inferred from the FastAPI code.
 * This is used to represent invoices.
 */
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

    private String ref;
    private Double cost;



    // Getters and Setters
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
}

