package com.app.repository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.app.dto.EnhancedPayResponse;
import com.app.model.Jobby;
import com.app.model.Pay;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PayRepositoryNative {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
    public EnhancedPayResponse getPayWithInvoice(Long code) {
        // Get pay record using SELECT *
        String payQuery = "SELECT code, amount, invoice_id, approved_by, create_at, due_at, pay_via, supply_invoice, FROM pay WHERE code = :code";
        List<Pay> pays = entityManager.createNativeQuery(payQuery, Pay.class)
                .setParameter("code", code)
                .getResultList();
        
        if (pays.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pay not found");
        }
        
        Pay pay = pays.get(0);
        Jobby invoice = null;
        
        // Get invoice if invoice_id exists
        if (pay.getInvoiceId() != null) {
            // Use SELECT * to get all columns that match the entity mapping
            String invoiceQuery = "SELECT * FROM jobby WHERE code = :invoiceId";
            
            List<Jobby> invoices = entityManager.createNativeQuery(invoiceQuery, Jobby.class)
                    .setParameter("invoiceId", pay.getInvoiceId())
                    .getResultList();
            
            invoice = invoices.isEmpty() ? null : invoices.get(0);
        }
        
        return new EnhancedPayResponse(pay, invoice);
    }
}