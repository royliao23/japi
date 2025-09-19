package com.app.dto;

import java.time.OffsetDateTime;

public class PayRequest {
    private Integer code;
    private Double amount;
    private String payVia;
    private Integer invoiceId;
    private String supplyInvoice;
    private String approvedBy;
    private String note;
    private OffsetDateTime createAt;
    private OffsetDateTime updatedAt;

    // Getters and setters
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getPayVia() { return payVia; }
    public void setPayVia(String payVia) { this.payVia = payVia; }
    public Integer getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Integer invoiceId) { this.invoiceId = invoiceId; }
    public String getSupplyInvoice() { return supplyInvoice; }
    public void setSupplyInvoice(String supplyInvoice) { this.supplyInvoice = supplyInvoice; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public OffsetDateTime getCreateAt() { return createAt; }
    public void setCreateAt(OffsetDateTime createAt) { this.createAt = createAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}

