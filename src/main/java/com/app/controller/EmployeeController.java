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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmployeeRequest;
import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeUpdateRequest;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("/high/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // POST - Create Employee
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest request) {
        try {
            EmployeeResponse response = employeeService.createEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Database error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // GET ALL - Get all employees (paginated with department info)
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "100") int limit) {
        try {
            List<EmployeeResponse> employees = employeeService.getAllEmployees(skip, limit);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET ALL - Get all employees (non-paginated with department info)
    @GetMapping("all/")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        try {
            List<EmployeeResponse> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET ONE - Get employee by ID
    @GetMapping("{employeeId}/")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long employeeId) {
        try {
            EmployeeResponse employee = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT - Update employee
    @PutMapping("{employeeId}/")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId,
                                           @RequestBody EmployeeUpdateRequest request) {
        try {
            EmployeeResponse response = employeeService.updateEmployee(employeeId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            if (e.getMessage().contains("not found")) {
                errorResponse.put("error", "Not Found");
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            } else {
                errorResponse.put("error", "Bad Request");
                errorResponse.put("message", e.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Database error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // DELETE - Delete employee
    @DeleteMapping("{employeeId}/")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
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