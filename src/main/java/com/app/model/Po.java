package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * PO Model that represents a purchase order entity in the database.
 * This class corresponds to the 'po' Pydantic model in the FastAPI code.
 */
@Entity
@Table(name = "purchase_order")
public class Po {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;

    @NotNull
    private Double cost;

    private String description;

    @NotNull
    @JsonProperty("job_id")
    @Column(name = "job_id")
    private Long jobId;

    @NotNull
    @JsonProperty("by_id")
    @Column(name = "by_id")
    private Long byId;

    @NotNull
    @JsonProperty("project_id")
    @Column(name = "project_id")
    private Long projectId;

    private String ref;

    @JsonProperty("due_at")
    @Column(name = "due_at")
    private LocalDate dueAt;

    private String contact;

    private String note;

    @CreationTimestamp
    @JsonProperty("create_at")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Po() {
    }

    public Po(Double cost, String description, Long jobId, Long byId, Long projectId, String ref, LocalDate dueAt, String contact, String note) {
        this.cost = cost;
        this.description = description;
        this.jobId = jobId;
        this.byId = byId;
        this.projectId = projectId;
        this.ref = ref;
        this.dueAt = dueAt;
        this.contact = contact;
        this.note = note;
    }

    // Getters and Setters
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public LocalDate getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDate dueAt) {
        this.dueAt = dueAt;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Po po = (Po) o;
        return Objects.equals(code, po.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}

