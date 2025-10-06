package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRoleResponse {
    @JsonProperty("user_id")
    private Long userId;
    
    private String role;
    
    private String message;

    // Constructors
    public CreateUserRoleResponse() {}

    public CreateUserRoleResponse(Long userId, String role, String message) {
        this.userId = userId;
        this.role = role;
        this.message = message;
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
