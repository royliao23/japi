package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectDataResponse {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("invoiced")
    private Double invoiced;
    
    @JsonProperty("paid")
    private Double paid;

    // Constructors
    public ProjectDataResponse() {}

    public ProjectDataResponse(String name, Double invoiced, Double paid) {
        this.name = name;
        this.invoiced = invoiced;
        this.paid = paid;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getInvoiced() { return invoiced; }
    public void setInvoiced(Double invoiced) { this.invoiced = invoiced; }

    public Double getPaid() { return paid; }
    public void setPaid(Double paid) { this.paid = paid; }
}
