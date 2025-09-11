package com.app.repository;

import com.app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A Spring Data JPA repository for the Job entity.
 * It provides methods for database operations without needing explicit SQL.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Custom method to find jobs by a list of job category IDs.
     * Spring Data JPA automatically generates the query from the method name.
     *
     * @param jobCategoryIds A list of category IDs to search for.
     * @return A list of jobs that belong to the specified categories.
     */
    List<Job> findByJobCategoryIdIn(List<Long> jobCategoryIds);
}

