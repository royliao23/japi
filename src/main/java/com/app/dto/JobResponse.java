package com.app.dto;

public class JobResponse {
    private Long code;
    private String name;

    public JobResponse(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getters and Setters
    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}