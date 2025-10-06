package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CreateUserRoleResponse;
import com.app.dto.MessageResponse;
import com.app.dto.UserRoleRequest;
import com.app.dto.UserRoleResponse;
import com.app.model.UserRole;
import com.app.repository.UserRoleRepository;

@Service
@Transactional
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    // CREATE
    public CreateUserRoleResponse createUserRole(UserRoleRequest request) {
        UserRole userRole = new UserRole();
        userRole.setUserId(request.getUserId());
        userRole.setRole(request.getRole());

        UserRole savedUserRole = userRoleRepository.save(userRole);
        
        return new CreateUserRoleResponse(
            savedUserRole.getUserId(),
            savedUserRole.getRole(),
            "UserRole created successfully"
        );
    }

    // READ ALL
    public List<UserRoleResponse> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAllByOrderById();
        return userRoles.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // READ SINGLE by user_id
    public UserRoleResponse getUserRoleByUserId(Long userId) {
        UserRole userRole = userRoleRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("UserRole not found with user_id: " + userId));
        return convertToResponse(userRole);
    }

    // UPDATE
    public MessageResponse updateUserRole(Long userRoleId, UserRoleRequest request) {
        UserRole existingUserRole = userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new RuntimeException("UserRole not found with id: " + userRoleId));

        existingUserRole.setUserId(request.getUserId());
        existingUserRole.setRole(request.getRole());

        userRoleRepository.save(existingUserRole);
        
        return new MessageResponse("UserRole updated successfully");
    }

    // DELETE
    public MessageResponse deleteUserRole(Long userRoleId) {
        if (!userRoleRepository.existsById(userRoleId)) {
            throw new RuntimeException("UserRole not found with id: " + userRoleId);
        }
        
        userRoleRepository.deleteById(userRoleId);
        return new MessageResponse("UserRole deleted successfully");
    }

    // Helper method to convert entity to response DTO
    private UserRoleResponse convertToResponse(UserRole userRole) {
        return new UserRoleResponse(
            userRole.getId(),
            userRole.getUserId(),
            userRole.getRole()
        );
    }
}
