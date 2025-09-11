package com.app.controller;

import com.app.dto.JobCreationResponse;
import com.app.dto.JobsByCategoryRequest;
import com.app.model.Job;
import com.app.repository.JobRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for managing jobs.
 * This class handles all the HTTP requests and corresponds to the APIRouter in FastAPI.
 */
@RestController
@RequestMapping("/high/job/")
public class JobController {

    private final JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Creates a new job.
     * This method corresponds to the `create_job` endpoint in the FastAPI code.
     *
     * @param job The job data from the request body.
     * @return A ResponseEntity containing the created job's details.
     */
    @PostMapping
    public ResponseEntity<JobCreationResponse> createJob(@Valid @RequestBody Job job) {
        Job savedJob = jobRepository.save(job);
        JobCreationResponse response = new JobCreationResponse(
            savedJob.getCode(),
            savedJob.getName(),
            savedJob.getJobCategoryId(),
            savedJob.getDescription()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all jobs.
     * This method corresponds to the `read_jobs` endpoint in the FastAPI code.
     *
     * @return A list of all jobs.
     */
    @GetMapping
    public List<Job> readJobs() {
        return jobRepository.findAll();
    }

    /**
     * Retrieves a single job by its ID.
     * This method corresponds to the `read_job` endpoint in the FastAPI code.
     *
     * @param jobId The ID of the job to retrieve.
     * @return The found job.
     * @throws ResponseStatusException if the job is not found.
     */
    @GetMapping("{jobId}/")
    public Job readJobById(@PathVariable Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
    }

    /**
     * Updates an existing job.
     * This method corresponds to the `update_job` endpoint in the FastAPI code.
     *
     * @param jobId The ID of the job to update.
     * @param updatedJob The updated job data from the request body.
     * @return A ResponseEntity with a success message.
     * @throws ResponseStatusException if the job is not found.
     */
    @PutMapping("{jobId}/")
    public ResponseEntity<Map<String, String>> updateJob(@PathVariable Long jobId, @Valid @RequestBody Job updatedJob) {
        Job jobToUpdate = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));

        jobToUpdate.setName(updatedJob.getName());
        jobToUpdate.setJobCategoryId(updatedJob.getJobCategoryId());
        jobToUpdate.setDescription(updatedJob.getDescription());

        jobRepository.save(jobToUpdate);

        return ResponseEntity.ok(Collections.singletonMap("message", "job updated successfully"));
    }

    /**
     * Deletes a job by its ID.
     * This method corresponds to the `delete_job` endpoint in the FastAPI code.
     *
     * @param jobId The ID of the job to delete.
     * @return A ResponseEntity with a success message.
     * @throws ResponseStatusException if the job is not found.
     */
    @DeleteMapping("{jobId}/")
    public ResponseEntity<Map<String, String>> deleteJob(@PathVariable Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found");
        }
        jobRepository.deleteById(jobId);
        return ResponseEntity.ok(Collections.singletonMap("message", "job deleted successfully"));
    }

    /**
     * Retrieves a list of jobs based on a list of category IDs.
     * This method corresponds to the `jobs-by-category` endpoint in the FastAPI code.
     *
     * @param requestBody A request body containing a list of category IDs.
     * @return A list of jobs that match the provided category IDs.
     */
    @PostMapping("jobs-by-category/")
    public List<Job> getJobsByCategory(@RequestBody JobsByCategoryRequest requestBody) {
        if (requestBody.getCategoryCodes() == null || requestBody.getCategoryCodes().isEmpty()) {
            return Collections.emptyList();
        }
        return jobRepository.findByJobCategoryIdIn(requestBody.getCategoryCodes());
    }
}

