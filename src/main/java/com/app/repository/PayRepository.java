package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.dto.PayResponse;
import com.app.model.Pay;

/**
 * A Spring Data JPA repository for the Pay entity.
 */
@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    // Simplify this method - remove the constructor expression
    @Query("SELECT p FROM Pay p WHERE p.invoiceId = :invoiceId")
    List<Pay> findPaymentsByInvoiceId(@Param("invoiceId") Long invoiceId);
    
    // Use the correct constructor with proper field order
    @Query("SELECT new com.app.dto.PayResponse(p.code, p.amount, p.status, p.ref, p.note, p.createAt, p.updatedAt) " +
           "FROM Pay p WHERE p.invoiceId = :invoiceId")
    List<PayResponse> findPayResponseByInvoiceId(@Param("invoiceId") Long invoiceId);
    List<Pay> findByInvoiceId(Long invoiceId);
    List<Pay> findAllByOrderByCodeDesc();

    @Query("SELECT SUM(p.amount) FROM Pay p WHERE p.invoiceId = ?1")
    Double sumAmountByInvoiceId(Long invoiceId);

    @Query("SELECT p FROM Pay p WHERE p.approvedBy LIKE %?1% OR p.supplyInvoice LIKE %?1%")
    List<Pay> findByKeyword(String keyword);

    Optional<Pay> findByCode(Long code);
    
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Pay p WHERE p.invoiceId = :invoiceId")
    Double getTotalPaidByInvoiceId(@Param("invoiceId") Long invoiceId);
}
