package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Pay;

/**
 * A Spring Data JPA repository for the Pay entity.
 */
@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {

    List<Pay> findByInvoiceId(Long invoiceId);
    List<Pay> findAllByOrderByCodeDesc();

    @Query("SELECT SUM(p.amount) FROM Pay p WHERE p.invoiceId = ?1")
    Double sumAmountByInvoiceId(Long invoiceId);

    @Query("SELECT p FROM Pay p WHERE p.approvedBy LIKE %?1% OR p.supplyInvoice LIKE %?1%")
    public List<Pay> findByKeyword(String keyword);

    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.invoiceId = :invoiceId")
    Double getTotalPaidByInvoiceId(@Param("invoiceId") Long invoiceId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Invoice i SET i.paid = :paid, i.status = :status WHERE i.code = :invoiceId")
    void updateInvoicePayment(@Param("invoiceId") Long invoiceId,
                             @Param("paid") Double paid, 
                             @Param("status") String status);

}
