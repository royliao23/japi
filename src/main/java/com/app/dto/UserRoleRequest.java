package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRoleRequest {
    @JsonProperty("user_id")
    private Long userId;
    
    private String role;

    // Constructors
    public UserRoleRequest() {}

    public UserRoleRequest(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
