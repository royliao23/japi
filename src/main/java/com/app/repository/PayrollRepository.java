package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    boolean existsById(Long id);
}