package com.example.repository;

import java.util.List;

import com.example.model.JmProject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JmProjectRepository extends JpaRepository<JmProject, Integer> {
    // Custom methods can be added here
    List<JmProject> findByStatus(String status);
}