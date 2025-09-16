package com.app.service;

// import com.app.dto.InvoiceWithPaymentsResponse;
// import com.app.dto.PaymentResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Invoice;
import com.app.repository.InvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InvoiceService {
    
    private final InvoiceRepository invoiceRepository;
    // private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;
    
    public InvoiceService(InvoiceRepository invoiceRepository, 
                         //PaymentRepository paymentRepository,
                         ObjectMapper objectMapper) {
        this.invoiceRepository = invoiceRepository;
        // this.paymentRepository = paymentRepository;
        this.objectMapper = objectMapper;
    }
    
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    public List<Invoice> getUnpaidInvoices() {
        return invoiceRepository.findByStatusNotOrderByCodeDesc("paid");
    }
    
    
    public Optional<Invoice> getInvoiceById(Integer id) {
        return invoiceRepository.findById(id);
    }
    
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    
    public Invoice updateInvoice(Integer id, Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(id)
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
    
    public Invoice updateInvoiceStatus(Integer id, String status) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
        invoice.setStatus(status);
        return invoiceRepository.save(invoice);
    }
    
    @Transactional
    public void deleteInvoice(Integer id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Invoice not found");
        }
        invoiceRepository.deleteById(id);
    }
    
    public List<Invoice> getInvoicesByJobsAndProject(List<Integer> jobCodes, Integer projectCode) {
        return invoiceRepository.findByJobIdsAndProjectId(jobCodes, projectCode);
    }
    // public List<InvoiceWithPaymentsResponse> getInvoicesWithPayments() {
    //     List<Invoice> invoices = invoiceRepository.findAll();
    //     List<InvoiceWithPaymentsResponse> result = new ArrayList<>();
        
    //     for (Invoice invoice : invoices) {
    //         InvoiceWithPaymentsResponse response = convertToResponse(invoice);
    //         // Get payments for this invoice
    //         List<PaymentResponse> payments = paymentRepository.findPaymentsByInvoiceId(invoice.getCode());
    //         response.setPayments(payments);
    //         result.add(response);
    //     }
        
    //     return result;
    // }
    
    // private InvoiceWithPaymentsResponse convertToResponse(Invoice invoice) {
    //     InvoiceWithPaymentsResponse response = new InvoiceWithPaymentsResponse();
    //     response.setCode(invoice.getCode());
    //     response.setPoId(invoice.getPoId());
    //     response.setRef(invoice.getRef());
    //     response.setCost(invoice.getCost());
    //     response.setPaid(invoice.getPaid());
    //     response.setDueAt(invoice.getDueAt());
    //     response.setUpdatedAt(invoice.getUpdatedAt());
    //     response.setContact(invoice.getContact());
    //     response.setNote(invoice.getNote());
    //     response.setCreateAt(invoice.getCreateAt() != null ? invoice.getCreateAt().toLocalDate() : null);
    //     response.setDescription(invoice.getDescription());
    //     response.setJobId(invoice.getJobId());
    //     response.setById(invoice.getById());
    //     response.setProjectId(invoice.getProjectId());
    //     response.setStatus(invoice.getStatus());
        
    //     return response;
    //}
}