package com.app.repository;

import com.app.model.Po;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A Spring Data JPA repository for the Po entity.
 */
@Repository
public interface PoRepository extends JpaRepository<Po, Long> {

}

