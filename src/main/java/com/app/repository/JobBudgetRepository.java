package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.JobBudget;

@Repository
public interface JobBudgetRepository extends JpaRepository<JobBudget, Long> {
    
    @Query("SELECT jb FROM JobBudget jb " +
           "LEFT JOIN FETCH jb.job " +
           "LEFT JOIN FETCH jb.project " +
           "WHERE jb.code = :code")
    Optional<JobBudget> findByIdWithJoins(@Param("code") Long code);
    
    @Query("SELECT jb FROM JobBudget jb " +
           "LEFT JOIN FETCH jb.job " +
           "LEFT JOIN FETCH jb.project")
    List<JobBudget> findAllWithJoins();
}