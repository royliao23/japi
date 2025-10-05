package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Job;

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
    @Query(value = "SELECT * FROM job WHERE job_category_id IN :categoryCodes", nativeQuery = true)
    List<Job> findJobsByCategoryCodesNative(@Param("categoryCodes") List<Long> categoryCodes);
}

