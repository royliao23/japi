package com.app.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class InvoiceRequest {
    private Double cost;
    private Double paid;
    private String description;
    private Integer poId;
    private Integer jobId;
    private Integer byId;
    private Integer projectId;
    private String ref;
    private LocalDate dueAt;
    private String contact;
    private String status;
    private String note;
    private OffsetDateTime createAt;
    private OffsetDateTime updatedAt;

    // Getters and Setters
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Double getPaid() { return paid; }
    public void setPaid(Double paid) { this.paid = paid; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPoId() { return poId; }
    public void setPoId(Integer poId) { this.poId = poId; }

    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }

    public Integer getById() { return byId; }
    public void setById(Integer byId) { this.byId = byId; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }

    public LocalDate getDueAt() { return dueAt; }
    public void setDueAt(LocalDate dueAt) { this.dueAt = dueAt; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public OffsetDateTime getCreateAt() { return createAt; }
    public void setCreateAt(OffsetDateTime createAt) { this.createAt = createAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
