package com.app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_user")
public class AuthUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true, length = 150)
    private String username;
    
    @Column(nullable = false, length = 128)
    private String password;
    
    @Column(nullable = false, length = 254)
    private String email;
    
    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    
    @Column(name = "is_staff", nullable = false)
    private Boolean isStaff;
    
    @Column(name = "is_superuser", nullable = false)
    private Boolean isSuperuser;
    
    @Column(name = "date_joined", nullable = false)
    private LocalDateTime dateJoined;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    // Fix all getters that throw exceptions
    public String getUsername() {
        return this.username;  // Instead of throwing exception
    }

    public String getPassword() {
        return this.password;  // Instead of throwing exception
    }

    public Boolean getIsSuperuser() {
        return this.isSuperuser;  // Instead of throwing exception
    }

    public Boolean getIsActive() {
        return this.isActive;  // Instead of throwing exception
    }

    public String getEmail() {
        return this.email;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public LocalDateTime getDateJoined() {
        return this.dateJoined;
    }

    // Fix all setters that throw exceptions
    public void setUsername(String username) {
        this.username = username;  // Instead of throwing exception
    }

    public void setPassword(String password) {
        this.password = password;  // Instead of throwing exception
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;  // Instead of throwing exception
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;  // Instead of throwing exception
    }

    public void setIsSuperuser(Boolean isSuperuser) {
        this.isSuperuser = isSuperuser;  // Instead of throwing exception
    }


    public void setEmail(String email) {
        this.email = email;  // Instead of throwing exception
    }

    public void setDateJoined(LocalDateTime now) {
        this.dateJoined = now;  // Instead of throwing exception
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;  // Instead of throwing exception
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;  // Instead of throwing exception
    }
}