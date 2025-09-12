package com.app.repository;

import com.app.model.Jobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A Spring Data JPA repository for the Jobby entity.
 */
@Repository
public interface JobbyRepository extends JpaRepository<Jobby, Long> {

    List<Jobby> findByPoId(Long poId);
}
