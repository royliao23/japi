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

import com.app.dto.PayrollRequest;
import com.app.dto.PayrollResponse;
import com.app.service.PayrollService;

@RestController
@RequestMapping("/high/payroll/")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    // GET ALL - Get all payrolls (paginated with employee info)
    @GetMapping
    public ResponseEntity<List<PayrollResponse>> getAllPayrolls(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "100") int limit) {
        try {
            List<PayrollResponse> payrolls = payrollService.getAllPayrolls(skip, limit);
            return ResponseEntity.ok(payrolls);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // POST - Create payroll
    @PostMapping
    public ResponseEntity<?> createPayroll(@RequestBody PayrollRequest request) {
        try {
            PayrollResponse response = payrollService.createPayroll(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Database error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    // GET ONE - Get payroll by ID
    @GetMapping("{payrollId}/")
    public ResponseEntity<?> getPayrollById(@PathVariable Long payrollId) {
        try {
            PayrollResponse payroll = payrollService.getPayrollById(payrollId);
            return ResponseEntity.ok(payroll);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Not Found");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT - Update payroll
    @PutMapping("{payrollId}/")
    public ResponseEntity<?> updatePayroll(@PathVariable Long payrollId,
                                          @RequestBody PayrollRequest request) {
        try {
            PayrollResponse response = payrollService.updatePayroll(payrollId, request);
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

    // DELETE - Delete payroll
    @DeleteMapping("{payrollId}/")
    public ResponseEntity<?> deletePayroll(@PathVariable Long payrollId) {
        try {
            payrollService.deletePayroll(payrollId);
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
