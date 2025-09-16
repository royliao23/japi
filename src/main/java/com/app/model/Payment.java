package com.app.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * A simple model for the `pay` table, inferred from the FastAPI code.
 */
@Entity
@Table(name = "pay")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @JsonProperty("invoice_id")
    @Column(name = "invoice_id")
    private Long invoiceId;

    private Double amount;
    private String payVia;
    @CreationTimestamp
    @JsonProperty("create_at")
    @Column(name = "create_at")
    private LocalDateTime createAt;
    // Getters and Setters
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayVia() {
        return payVia;
    }
    public void setPayVia(String payVia) {
        this.payVia = payVia;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

