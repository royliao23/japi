package com.app.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    
    @Column(name = "due_at")
    private LocalDate dueAt;
    
    private String status;
    private String ref;

    // Constructors
    public Pay() {
        // Default constructor required by JPA
    }

    // Optional: Add a constructor for creating new Pay objects
    public Pay(Double amount, String payVia, Long invoiceId, String supplyInvoice, 
               String approvedBy, String note, OffsetDateTime createAt, OffsetDateTime updatedAt) {
        this.amount = amount;
        this.payVia = payVia;
        this.invoiceId = invoiceId;
        this.supplyInvoice = supplyInvoice;
        this.approvedBy = approvedBy;
        this.note = note;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.calculateStatus(); // Calculate status based on amount
    }

    // Getters and setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { 
        this.amount = amount; 
        this.calculateStatus(); // Recalculate status when amount changes
    }
    
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
    
    public LocalDate getDueAt() { return dueAt; }
    public void setDueAt(LocalDate dueAt) { this.dueAt = dueAt; }
    
    // Fixed setStatus method - actually sets the field
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getRef() {
        return ref;
    }
    
    public void setRef(String ref) {
        this.ref = ref;
    }

    // Remove these problematic methods - they don't belong in an entity
    // public Pay orElseThrow(Object object) {
    //     throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    // }
    // 
    // public Pay orElse(Object object) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
    // 
    // public LocalDateTime getCreatedAt() {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

    // Helper method to calculate status based on amount
    public void calculateStatus() {
        if (amount != null && amount > 0) {
            this.status = "paid";
        } else {
            this.status = "unpaid";
        }
    }

    // JPA lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        if (createAt == null) {
            createAt = OffsetDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = OffsetDateTime.now();
        }
        if (status == null) {
            calculateStatus();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
        calculateStatus(); // Recalculate status on update
    }
}