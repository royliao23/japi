package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/* A Data Transfer Object (DTO) to format the response for the job creation endpoint. */

public class JobCreationResponse {

    @JsonProperty("id")
    private Long code;

    @JsonProperty("job_category_id")
    private Long jobCategoryId;

    private String name;

    private String description;

    public JobCreationResponse() {
    }

    public JobCreationResponse(Long code, String name, Long jobCategoryId, String description) {
        this.code = code;
        this.name = name;
        this.jobCategoryId = jobCategoryId;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Long jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

