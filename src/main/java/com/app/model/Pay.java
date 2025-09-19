package com.app.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pay")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private Double amount;
    @Column(name = "pay_via")
    private String payVia;
    @Column(name = "invoice_id")
    private Long invoiceId;
    @Column(name = "supply_invoice")
    private String supplyInvoice;
    @Column(name = "approved_by")
    private String approvedBy;
    private String note;
    @Column(name = "create_at")
    private OffsetDateTime createAt;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    // Getters and setters
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
    public OffsetDateTime getCreateAt() { return createAt; }
    public void setCreateAt(OffsetDateTime createAt) { this.createAt = createAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

    public void setStatus(String status) {
        if (amount != null && amount > 0) {
            // Example logic: if amount is greater than 0, status is "paid"
            status = "paid";
        } else {
            status = "unpaid";
        }
    }
}
