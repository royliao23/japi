package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.JobBudgetRequest;
import com.app.dto.JobBudgetResponse;
import com.app.dto.JobResponse;
import com.app.dto.ProjectResponse;
import com.app.model.JobBudget;
import com.app.repository.JobBudgetRepository;

@Service
@Transactional
public class JobBudgetService {

    @Autowired
    private JobBudgetRepository jobBudgetRepository;

    // CREATE
    public JobBudgetResponse createJobBudget(JobBudgetRequest request) {
        JobBudget jobBudget = new JobBudget();
        jobBudget.setJobId(request.getJobId());
        jobBudget.setProjectId(request.getProjectId());
        jobBudget.setBudget(request.getBudget());
        jobBudget.setNote(request.getNote());

        JobBudget saved = jobBudgetRepository.save(jobBudget);
        return convertToResponse(saved);
    }

    // READ (Single)
    public JobBudgetResponse getJobBudgetById(Long budgetId) {
        JobBudget jobBudget = jobBudgetRepository.findByIdWithJoins(budgetId)
                .orElseThrow(() -> new RuntimeException("Job budget not found with id: " + budgetId));
        return convertToResponse(jobBudget);
    }

    // READ (All)
    public List<JobBudgetResponse> getAllJobBudgets() {
        List<JobBudget> jobBudgets = jobBudgetRepository.findAllWithJoins();
        return jobBudgets.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE
    public JobBudgetResponse updateJobBudget(Long budgetId, JobBudgetRequest request) {
        JobBudget existing = jobBudgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Job budget not found with id: " + budgetId));

        // Update fields if provided
        if (request.getJobId() != null) {
            existing.setJobId(request.getJobId());
        }
        if (request.getProjectId() != null) {
            existing.setProjectId(request.getProjectId());
        }
        if (request.getBudget() != null) {
            existing.setBudget(request.getBudget());
        }
        if (request.getNote() != null) {
            existing.setNote(request.getNote());
        }

        JobBudget updated = jobBudgetRepository.save(existing);
        return convertToResponse(updated);
    }

    // DELETE
    public void deleteJobBudget(Long budgetId) {
        if (!jobBudgetRepository.existsById(budgetId)) {
            throw new RuntimeException("Job budget not found with id: " + budgetId);
        }
        jobBudgetRepository.deleteById(budgetId);
    }

    // Helper method to convert entity to response DTO
    private JobBudgetResponse convertToResponse(JobBudget jobBudget) {
        JobResponse jobResponse = null;
        if (jobBudget.getJob() != null) {
            jobResponse = new JobResponse(
                jobBudget.getJob().getCode(),
                jobBudget.getJob().getName()
            );
        }

        ProjectResponse projectResponse = null;
        if (jobBudget.getProject() != null) {
            projectResponse = new ProjectResponse(
                jobBudget.getProject().getCode(),
                jobBudget.getProject().getProjectName()
            );
        }

        return new JobBudgetResponse(
            jobBudget.getCode(),
            jobBudget.getJobId(),
            jobBudget.getProjectId(),
            jobBudget.getBudget(),
            jobBudget.getNote(),
            jobResponse,
            projectResponse
        );
    }
}