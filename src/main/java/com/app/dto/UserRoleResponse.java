package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRoleResponse {
    private Long id;
    
    @JsonProperty("user_id")
    private Long userId;
    
    private String role;

    // Constructors
    public UserRoleResponse() {}

    public UserRoleResponse(Long id, Long userId, String role) {
        this.id = id;
        this.userId = userId;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
