package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Job Model that represents a job entity in the database.
 * This class corresponds to the 'job' Pydantic model in the FastAPI code.
 */
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("code")
    @Column(name = "code")
    private Long code;

    @NotNull(message = "Job category ID is mandatory")
    @JsonProperty("job_category_id")
    @Column(name = "job_category_id")
    private Long jobCategoryId;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Lob // For larger text fields
    private String description;

    public Job() {
    }

    public Job(Long jobCategoryId, String name, String description) {
        this.jobCategoryId = jobCategoryId;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Long jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(code, job.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}

