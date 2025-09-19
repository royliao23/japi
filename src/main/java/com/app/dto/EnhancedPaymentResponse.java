package com.app.dto;

public class EnhancedPaymentResponse {
    private Long code;
    private Double amount;
    private String payVia;
    private Long invoiceId;
    private String supplyInvoice;
    private String approvedBy;
    private String note;
    private String createAt;
    private String updatedAt;
    private Object jobby; // Can be Invoice or Map
    
    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getPayVia() { return payVia; }
    public void setPayVia(String payVia) { this.payVia = payVia; }
    
    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }
    
    public String getSupplyInvoice() { return supplyInvoice; }
    public void setSupplyInvoice(String supplyInvoice) { this.supplyInvoice = supplyInvoice; }
    
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    
    public String getCreateAt() { return createAt; }
    public void setCreateAt(String createAt) { this.createAt = createAt; }
    
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    
    public Object getJobby() { return jobby; }
    public void setJobby(Object jobby) { this.jobby = jobby; }
}
