package com.app.dto;

import java.time.OffsetDateTime;

public class PayResponse {
    private Long code;
    private Double amount;
    private String status;
    private String ref;
    private String note;
    private OffsetDateTime createAt;
    private OffsetDateTime updatedAt;
    
    // Default constructor
    public PayResponse() {}
    
    // Constructor for JPQL query
    public PayResponse(Long code, Double amount, String status, String ref, String note, 
                      OffsetDateTime createAt, OffsetDateTime updatedAt) {
        this.code = code;
        this.amount = amount;
        this.status = status;
        this.ref = ref;
        this.note = note;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public OffsetDateTime getCreateAt() { return createAt; }
    public void setCreateAt(OffsetDateTime createAt) { this.createAt = createAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}