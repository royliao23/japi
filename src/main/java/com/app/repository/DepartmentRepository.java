package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find all departments ordered by id descending (for pagination)
    List<Department> findAllByOrderByIdDesc();

    // Paginated version
    Page<Department> findAllByOrderByIdDesc(Pageable pageable);

    // Check if department exists by id
    boolean existsById(Long id);

    // Find department by id
    Optional<Department> findById(Long id);
}
