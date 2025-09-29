package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Jobby;

/**
 * A Spring Data JPA repository for the Jobby entity.
 */
@Repository
public interface JobbyRepository extends JpaRepository<Jobby, Long> {

    static Jobby findByCode(Long code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCode'");
    }

    List<Jobby> findByPoId(Long poId);
}
