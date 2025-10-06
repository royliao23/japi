package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CreateUserRoleResponse;
import com.app.dto.MessageResponse;
import com.app.dto.UserRoleRequest;
import com.app.dto.UserRoleResponse;
import com.app.service.UserRoleService;

@RestController
@RequestMapping("/high/userrole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    // POST - Create UserRole
    @PostMapping
    public ResponseEntity<?> createUserRole(@RequestBody UserRoleRequest request) {
        try {
            CreateUserRoleResponse response = userRoleService.createUserRole(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Database error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // GET ALL - Get all UserRoles
    @GetMapping
    public ResponseEntity<List<UserRoleResponse>> getAllUserRoles() {
        try {
            List<UserRoleResponse> userRoles = userRoleService.getAllUserRoles();
            return ResponseEntity.ok(userRoles);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET SINGLE - Get UserRole by user_id
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserRoleByUserId(@PathVariable Long userId) {
        try {
            UserRoleResponse userRole = userRoleService.getUserRoleByUserId(userId);
            return ResponseEntity.ok(userRole);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT - Update UserRole
    @PutMapping("/{userRoleId}")
    public ResponseEntity<?> updateUserRole(@PathVariable Long userRoleId, 
                                           @RequestBody UserRoleRequest request) {
        try {
            MessageResponse response = userRoleService.updateUserRole(userRoleId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Database error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // DELETE - Delete UserRole
    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<?> deleteUserRole(@PathVariable Long userRoleId) {
        try {
            MessageResponse response = userRoleService.deleteUserRole(userRoleId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Database error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
