package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobDataResponse {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("invoiced")
    private Double invoiced;
    
    @JsonProperty("paid")
    private Double paid;
    
    @JsonProperty("budget")
    private Double budget;

    // Constructors
    public JobDataResponse() {}

    public JobDataResponse(String name, Double invoiced, Double paid, Double budget) {
        this.name = name;
        this.invoiced = invoiced;
        this.paid = paid;
        this.budget = budget;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getInvoiced() { return invoiced; }
    public void setInvoiced(Double invoiced) { this.invoiced = invoiced; }

    public Double getPaid() { return paid; }
    public void setPaid(Double paid) { this.paid = paid; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
}
