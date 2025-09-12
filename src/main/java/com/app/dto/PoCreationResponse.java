package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO to format the response for the PO creation endpoint, mirroring the FastAPI output.
 */
public class PoCreationResponse {

    private Long code;
    private Double cost;
    private String description;
    @JsonProperty("job_id")
    private Long jobId;
    @JsonProperty("by_id")
    private Long byId;
    @JsonProperty("project_id")
    private Long projectId;
    private String ref;
    @JsonProperty("due_at")
    private LocalDate dueAt;
    private String contact;
    private String note;
    @JsonProperty("create_at")
    private LocalDateTime createAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public PoCreationResponse() {}

    public PoCreationResponse(Long code, Double cost, String description, Long jobId, Long byId, Long projectId, String ref, LocalDate dueAt, String contact, String note, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.code = code;
        this.cost = cost;
        this.description = description;
        this.jobId = jobId;
        this.byId = byId;
        this.projectId = projectId;
        this.ref = ref;
        this.dueAt = dueAt;
        this.contact = contact;
        this.note = note;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public Long getCode() { return code; }
    public Double getCost() { return cost; }
    public String getDescription() { return description; }
    public Long getJobId() { return jobId; }
    public Long getById() { return byId; }
    public Long getProjectId() { return projectId; }
    public String getRef() { return ref; }
    public LocalDate getDueAt() { return dueAt; }
    public String getContact() { return contact; }
    public String getNote() { return note; }
    public LocalDateTime getCreateAt() { return createAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters
    public void setCode(Long code) { this.code = code; }
    public void setCost(Double cost) { this.cost = cost; }
    public void setDescription(String description) { this.description = description; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public void setById(Long byId) { this.byId = byId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setRef(String ref) { this.ref = ref; }
    public void setDueAt(LocalDate dueAt) { this.dueAt = dueAt; }
    public void setContact(String contact) { this.contact = contact; }
    public void setNote(String note) { this.note = note; }
    public void setCreateAt(LocalDateTime createAt) { this.createAt = createAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

