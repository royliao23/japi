package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DepartmentRequest;
import com.app.dto.DepartmentResponse;
import com.app.dto.DepartmentUpdateRequest;
import com.app.model.Department;
import com.app.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // CREATE
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        department.setDescription(request.getDescription());
        department.setManager(request.getManager());

        Department savedDepartment = departmentRepository.save(department);
        return convertToResponse(savedDepartment);
    }

    // READ ALL (paginated)
    public List<DepartmentResponse> getAllDepartments(int skip, int limit) {
        Pageable pageable = PageRequest.of(skip / limit, limit);
        Page<Department> departmentPage = departmentRepository.findAllByOrderByIdDesc(pageable);
        
        return departmentPage.getContent().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // READ ALL (non-paginated)
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAllByOrderByIdDesc();
        return departments.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // READ ONE
    public DepartmentResponse getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
        return convertToResponse(department);
    }

    // UPDATE
    public DepartmentResponse updateDepartment(Long departmentId, DepartmentUpdateRequest request) {
        // Check if any fields are provided for update
        if (!request.hasUpdates()) {
            throw new RuntimeException("No data provided for update");
        }

        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));

        // Update only the provided fields
        if (request.getDepartmentName() != null) {
            existingDepartment.setDepartmentName(request.getDepartmentName());
        }
        if (request.getDescription() != null) {
            existingDepartment.setDescription(request.getDescription());
        }
        if (request.getManager() != null) {
            existingDepartment.setManager(request.getManager());
        }

        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return convertToResponse(updatedDepartment);
    }

    // DELETE
    public void deleteDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new RuntimeException("Department not found with id: " + departmentId);
        }
        departmentRepository.deleteById(departmentId);
    }

    // Helper method to convert entity to response DTO
    private DepartmentResponse convertToResponse(Department department) {
        return new DepartmentResponse(
            department.getId(),
            department.getDepartmentName(),
            department.getDescription(),
            department.getManager()
        );
    }
}
