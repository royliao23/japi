package com.app.repository;

/**
 * A Data Transfer Object (DTO) to format the response for the project creation endpoint.
 * This is to ensure the response keys match the original FastAPI code.
 * Removed Lombok annotations.
 */
public class ProjectCreationResponse {
    private Long code;
    private String name;
    private String message;

    public ProjectCreationResponse() {
    }

    public ProjectCreationResponse(Long code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
