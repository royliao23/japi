package com.app.repository;

import com.app.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A Spring Data JPA repository for the Pay entity.
 */
@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    List<Pay> findByInvoiceId(Long invoiceId);
}
