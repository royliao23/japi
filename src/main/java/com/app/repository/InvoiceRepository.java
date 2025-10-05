package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // not including "null status" invoices
    //List<Invoice> findByStatusNotOrderByCodeDesc(String status);
    
    @Query("SELECT i FROM Invoice i WHERE i.jobId IN :jobIds AND (:projectId IS NULL OR i.projectId = :projectId)")
    List<Invoice> findByJobIdsAndProjectId(@Param("jobIds") List<Long> jobIds,
                                          @Param("projectId") Long projectId);
    Invoice findByCode(Long code);

    // Add this to your repository and use it
    @Query("SELECT i FROM Invoice i WHERE i.status <> :status OR i.status IS NULL ORDER BY i.code DESC")
    List<Invoice> findByStatusNotOrderByCodeDesc(@Param("status") String status);
}