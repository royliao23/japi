package com.app.controller;

import java.util.List;

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

import com.app.dto.JobBudgetRequest;
import com.app.dto.JobBudgetResponse;
import com.app.service.JobBudgetService;

@RestController
@RequestMapping("/high/jobbudgets/")
public class JobBudgetController {

    @Autowired
    private JobBudgetService jobBudgetService;

    // CREATE
    @PostMapping
    public ResponseEntity<JobBudgetResponse> createJobBudget(@RequestBody JobBudgetRequest request) {
        JobBudgetResponse response = jobBudgetService.createJobBudget(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ (Single)
    @GetMapping("{budgetId}/")
    public ResponseEntity<JobBudgetResponse> getJobBudget(@PathVariable Long budgetId) {
        JobBudgetResponse response = jobBudgetService.getJobBudgetById(budgetId);
        return ResponseEntity.ok(response);
    }

    // READ (All)
    @GetMapping
    public ResponseEntity<List<JobBudgetResponse>> getAllJobBudgets() {
        List<JobBudgetResponse> responses = jobBudgetService.getAllJobBudgets();
        return ResponseEntity.ok(responses);
    }

    // UPDATE
    @PutMapping("{budgetId}/")
    public ResponseEntity<JobBudgetResponse> updateJobBudget(
            @PathVariable Long budgetId, 
            @RequestBody JobBudgetRequest request) {
        JobBudgetResponse response = jobBudgetService.updateJobBudget(budgetId, request);
        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("{budgetId}/")
    public ResponseEntity<Void> deleteJobBudget(@PathVariable Long budgetId) {
        jobBudgetService.deleteJobBudget(budgetId);
        return ResponseEntity.noContent().build();
    }
}
