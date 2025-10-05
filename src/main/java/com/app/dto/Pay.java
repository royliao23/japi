package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude null fields from JSON
public class Pay {
    @JsonProperty("amount")
    private Double amount;

    // Constructors
    public Pay() {}

    public Pay(Double amount) {
        this.amount = amount;
    }

    // Getters and Setters
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
