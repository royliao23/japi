package com.app.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceHistory {
    @JsonProperty("code")
    private Long code;
    
    @JsonProperty("due_at")
    private LocalDate dueAt;
    
    @JsonProperty("cost")
    private Double cost;
    
    @JsonProperty("ref")
    private String ref;
    
    @JsonProperty("create_at")
    private OffsetDateTime createAt;
    
    @JsonProperty("contractor")
    private Contractor contractor;
    
    @JsonProperty("pay")
    private List<Pay> pay;

    // Constructors
    public InvoiceHistory() {}

    public InvoiceHistory(Long code, LocalDate dueAt, Double cost, String ref, OffsetDateTime createAt, 
                  Contractor contractor, List<Pay> pay) {
        this.code = code;
        this.dueAt = dueAt;
        this.cost = cost;
        this.ref = ref;
        this.createAt = createAt;
        this.contractor = contractor;
        this.pay = pay;
    }

    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }

    public LocalDate getDueAt() { return dueAt; }
    public void setDueAt(LocalDate dueAt) { this.dueAt = dueAt; }

    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }

    public OffsetDateTime getCreateAt() { return createAt; }
    public void setCreateAt(OffsetDateTime createAt) { this.createAt = createAt; }

    public Contractor getContractor() { return contractor; }
    public void setContractor(Contractor contractor) { this.contractor = contractor; }

    public List<Pay> getPay() { return pay; }
    public void setPay(List<Pay> pay) { this.pay = pay; }
}
