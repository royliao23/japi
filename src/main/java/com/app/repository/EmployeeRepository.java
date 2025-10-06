package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find all employees ordered by id descending (for pagination)
    List<Employee> findAllByOrderByIdDesc();

    // Paginated version
    Page<Employee> findAllByOrderByIdDesc(Pageable pageable);

    // Check if employee exists by id
    boolean existsById(Long id);

    // Find employee by id
    Optional<Employee> findById(Long id);
}