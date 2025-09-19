package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Pay;

/**
 * A Spring Data JPA repository for the Pay entity.
 */
@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    List<Pay> findByInvoiceId(Long invoiceId);
    List<Pay> findAllByOrderByCodeDesc();

    @Query("SELECT SUM(p.amount) FROM Pay p WHERE p.invoiceId = ?1")
    Double sumAmountByInvoiceId(Integer invoiceId);

    @Query("SELECT p FROM Pay p WHERE p.approvedBy LIKE %?1% OR p.supplyInvoice LIKE %?1%")
    public List<Pay> findByKeyword(String keyword);

}
