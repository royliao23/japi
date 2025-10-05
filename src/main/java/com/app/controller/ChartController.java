package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDataResponse;
import com.app.dto.JobDataResponse;
import com.app.dto.PayeeDataResponse;
import com.app.dto.ProjectCodeResponse;
import com.app.dto.ProjectDataResponse;
import com.app.service.ChartService;

@RestController
@RequestMapping("/high/chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/project-data")
    public ResponseEntity<List<ProjectDataResponse>> getProjectData() {
        try {
            List<ProjectDataResponse> data = chartService.getProjectData();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/job-category-data/{projectId}")
    public ResponseEntity<List<CategoryDataResponse>> getJobCategoryData(@PathVariable Long projectId) {
        try {
            List<CategoryDataResponse> data = chartService.getJobCategoryData(projectId);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/job-data/{projectId}")
    public ResponseEntity<List<JobDataResponse>> getJobData(@PathVariable Long projectId) {
        try {
            List<JobDataResponse> data = chartService.getJobData(projectId);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/payee-data")
    public ResponseEntity<List<PayeeDataResponse>> getPayeeData() {
        try {
            List<PayeeDataResponse> data = chartService.getPayeeData();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/codes")
    public ResponseEntity<List<ProjectCodeResponse>> getProjectCodes() {
        try {
            List<ProjectCodeResponse> data = chartService.getProjectCodes();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
