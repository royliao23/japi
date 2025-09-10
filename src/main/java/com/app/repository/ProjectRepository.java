package com.app.repository;

import com.app.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A Spring Data JPA repository for the Project entity.
 * It provides methods for database operations without needing explicit SQL.
 * This file was missing, causing the 'cannot find symbol' errors in the controller.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Custom query to find all projects and map them to a DTO containing only
     * the code (id) and project name.
     */
    @Query("SELECT new com.app.repository.ProjectCodeNameDto(p.id, p.projectName) FROM Project p")
    List<ProjectCodeNameDto> findProjectCodeAndName();
}

