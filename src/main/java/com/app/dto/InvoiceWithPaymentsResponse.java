package com.app.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.app.model.Pay;
import com.fasterxml.jackson.annotation.JsonFormat;


public class InvoiceWithPaymentsResponse {
    private Long code;
    private Double cost;
    private Double paid;
    private String description;
    private Long poId;
    private Long jobId;
    private Long byId;
    private Long projectId;
    private String ref;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueAt;
    
    private String contact;
    private String status;
    private String note;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private OffsetDateTime createAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private OffsetDateTime updatedAt;

    private List<Pay> pay;

    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    
    public Double getPaid() { return paid; }
    public void setPaid(Double paid) { this.paid = paid; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Long getPoId() { return poId; }
    public void setPoId(Long poId) { this.poId = poId; }
    
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    
    public Long getById() { return byId; }
    public void setById(Long byId) { this.byId = byId; }
    
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    
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
    
    public List<Pay> getPay() { return pay; }
    public void setPay(List<Pay> pay) { this.pay = pay; }
}
