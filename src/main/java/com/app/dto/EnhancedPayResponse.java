package com.app.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class EnhancedPayResponse {
    private Long code;
    private Double amount;
    private String payVia;
    private Long invoice;
    private String supplyInvoice;
    private String approvedBy;
    private String note;
    private LocalDate createAt;  // Changed to LocalDate as per your example
    private OffsetDateTime updatedAt;
    private JobbyResponse jobby;
    
    // Constructors
    public EnhancedPayResponse() {}
    
    public EnhancedPayResponse(Long code, Double amount, String payVia, Long invoice, 
                             String supplyInvoice, String approvedBy, String note,
                             LocalDate createAt, OffsetDateTime updatedAt, JobbyResponse jobby) {
        this.code = code;
        this.amount = amount;
        this.payVia = payVia;
        this.invoice = invoice;
        this.supplyInvoice = supplyInvoice;
        this.approvedBy = approvedBy;
        this.note = note;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.jobby = jobby;
    }
    
    // Getters and setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getPayVia() { return payVia; }
    public void setPayVia(String payVia) { this.payVia = payVia; }
    
    public Long getInvoice() { return invoice; }
    public void setInvoice(Long invoice) { this.invoice = invoice; }
    
    public String getSupplyInvoice() { return supplyInvoice; }
    public void setSupplyInvoice(String supplyInvoice) { this.supplyInvoice = supplyInvoice; }
    
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    
    public LocalDate getCreateAt() { return createAt; }
    public void setCreateAt(LocalDate createAt) { this.createAt = createAt; }
    
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public JobbyResponse getJobby() { return jobby; }
    public void setJobby(JobbyResponse jobby) { this.jobby = jobby; }
}