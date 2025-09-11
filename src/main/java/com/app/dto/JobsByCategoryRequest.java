package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * DTO for the /jobs-by-category endpoint to handle the request body.
 */
public class JobsByCategoryRequest {

    @JsonProperty("category_codes")
    @NotEmpty(message = "Category codes list cannot be empty")
    private List<Long> categoryCodes;

    public JobsByCategoryRequest() {
    }

    public List<Long> getCategoryCodes() {
        return categoryCodes;
    }

    public void setCategoryCodes(List<Long> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }
}

