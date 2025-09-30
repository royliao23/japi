package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.InvoiceWithPaymentsResponse;
import com.app.dto.PayResponse;
import com.app.model.Invoice;
import com.app.repository.InvoiceRepository;
import com.app.repository.PayRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InvoiceService {
    
    private final InvoiceRepository invoiceRepository;
    private final PayRepository payRepository;
    // private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;
    
    public InvoiceService(InvoiceRepository invoiceRepository, 
                         PayRepository payRepository,
                         ObjectMapper objectMapper) {
        this.invoiceRepository = invoiceRepository;
        this.payRepository = payRepository;
        this.objectMapper = objectMapper;
    }
    
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    public List<Invoice> getUnpaidInvoices() {
        return invoiceRepository.findByStatusNotOrderByCodeDesc("paid");
    }
    
    
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }
    
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    
    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        invoice.setCost(invoiceDetails.getCost());
        invoice.setPaid(invoiceDetails.getPaid());
        invoice.setDescription(invoiceDetails.getDescription());
        invoice.setPoId(invoiceDetails.getPoId());
        invoice.setJobId(invoiceDetails.getJobId());
        invoice.setById(invoiceDetails.getById());
        invoice.setProjectId(invoiceDetails.getProjectId());
        invoice.setRef(invoiceDetails.getRef());
        invoice.setDueAt(invoiceDetails.getDueAt());
        invoice.setContact(invoiceDetails.getContact());
        invoice.setStatus(invoiceDetails.getStatus());
        invoice.setNote(invoiceDetails.getNote());
        invoice.setUpdatedAt(invoiceDetails.getUpdatedAt());
        
        return invoiceRepository.save(invoice);
    }
    
    public Invoice updateInvoiceStatus(Long id, String status) {
        Invoice invoice = invoiceRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        invoice.setStatus(status);
        return invoiceRepository.save(invoice);
    }
    
    @Transactional
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id.longValue())) {
            throw new RuntimeException("Invoice not found");
        }
        invoiceRepository.deleteById(id.longValue());
    }
    
    public List<Invoice> getInvoicesByJobsAndProject(List<Long> jobCodes, Long projectCode) {
        return invoiceRepository.findByJobIdsAndProjectId(jobCodes, projectCode);
    }
    public InvoiceWithPaymentsResponse getInvoiceWithPayments(Long invoiceId) {
    System.out.println("=== DEBUG: Looking for invoice ID: " + invoiceId + " ===");
    
    // Get the invoice
    Invoice invoice = invoiceRepository.findById(invoiceId)
            .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + invoiceId));
    
    System.out.println("Found invoice: " + invoice.getCode());
    System.out.println("Invoice details - Cost: " + invoice.getCost() + ", Status: " + invoice.getStatus());
    
    // Test the pay repository with a simple count first
    System.out.println("Testing pay repository with simple count...");
    try {
        long totalPayments = payRepository.count();
        System.out.println("Total payments in database: " + totalPayments);
    } catch (Exception e) {
        System.out.println("Error counting payments: " + e.getMessage());
        e.printStackTrace();
    }
    
    // Test with derived query method
    System.out.println("Testing derived query findByInvoiceId...");
    try {
        List<PayResponse> derivedPayments = payRepository.findPayResponseByInvoiceId(invoiceId);
        System.out.println("Derived query found: " + derivedPayments.size() + " payments");

        for (PayResponse pay : derivedPayments) {
            System.out.println("Payment: " + pay.getCode() + ", Amount: " + pay.getAmount());
        }
    } catch (Exception e) {
        System.out.println("Error with derived query: " + e.getMessage());
        e.printStackTrace();
    }
    
    // Test with custom query
    System.out.println("Testing custom query findPaymentsByInvoiceId...");
    try {
        List<PayResponse> payments = payRepository.findPayResponseByInvoiceId(invoiceId);
        System.out.println("Custom query found: " + payments.size() + " payments");
        
        // Convert to response DTO
        InvoiceWithPaymentsResponse response = new InvoiceWithPaymentsResponse();
        response.setCode(invoice.getCode());
        response.setCost(invoice.getCost());
        response.setPaid(invoice.getPaid());
        response.setDescription(invoice.getDescription());
        response.setPoId(invoice.getPoId());
        response.setJobId(invoice.getJobId());
        response.setById(invoice.getById());
        response.setProjectId(invoice.getProjectId());
        response.setRef(invoice.getRef());
        response.setDueAt(invoice.getDueAt());
        response.setContact(invoice.getContact());
        response.setStatus(invoice.getStatus());
        response.setNote(invoice.getNote());
        response.setCreateAt(invoice.getCreateAt());
        response.setUpdatedAt(invoice.getUpdatedAt());
        response.setPay(payments);
        
        System.out.println("Response prepared successfully for invoice: " + invoiceId);
        // System.out.println("Response prepared successfully for invoice with details: " + response.getPay());

        return response;
        
    } catch (Exception e) {
        System.out.println("Error with custom query: " + e.getMessage());
        e.printStackTrace();
        throw new RuntimeException("Failed to retrieve payments for invoice: " + invoiceId, e);
    }
    
}
}