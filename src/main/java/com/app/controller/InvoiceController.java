package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.InvoiceFilter;
import com.app.dto.InvoiceRequest;
import com.app.dto.StatusUpdate;
import com.app.model.Invoice;
import com.app.service.InvoiceService;

@RestController
@RequestMapping("/high/invoice/")
public class InvoiceController {
    
    private final InvoiceService invoiceService;
    
    // Constructor injection (recommended)
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        // Map fields from request to entity
        invoice.setCost(invoiceRequest.getCost());
        invoice.setPaid(invoiceRequest.getPaid());
        invoice.setDescription(invoiceRequest.getDescription());
        invoice.setPoId(invoiceRequest.getPoId());
        invoice.setJobId(invoiceRequest.getJobId());
        invoice.setById(invoiceRequest.getById());
        invoice.setProjectId(invoiceRequest.getProjectId());
        invoice.setRef(invoiceRequest.getRef());
        invoice.setDueAt(invoiceRequest.getDueAt());
        invoice.setContact(invoiceRequest.getContact());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setNote(invoiceRequest.getNote());
        invoice.setCreateAt(invoiceRequest.getCreateAt());
        invoice.setUpdatedAt(invoiceRequest.getUpdatedAt());


        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
    }
    
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }
    
    @GetMapping("unpaid/")
    public ResponseEntity<List<Invoice>> getUnpaidInvoices() {
        return ResponseEntity.ok(invoiceService.getUnpaidInvoices());
    }

    @GetMapping("invnpay/")
    public ResponseEntity<List<?>> getInvoicesWithPayments(
            @RequestParam(defaultValue = "true") boolean includePayments) {
        
        // if (includePayments) {
        //     return ResponseEntity.ok(invoiceService.getInvoicesWithPayments());
        // } else {
        //     List<Invoice> invoices = invoiceService.getAllInvoices();
        //     // Return as List<Invoice> when not including payments
        //     return ResponseEntity.ok(invoices);
        // }
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }
    
    @GetMapping("{id}/")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("{id}/")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, 
                                               @RequestBody InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        // Map fields from request to entity
        invoice.setCost(invoiceRequest.getCost());
        invoice.setPaid(invoiceRequest.getPaid());
        invoice.setDescription(invoiceRequest.getDescription());
        invoice.setPoId(invoiceRequest.getPoId());
        invoice.setJobId(invoiceRequest.getJobId());
        invoice.setById(invoiceRequest.getById());
        invoice.setProjectId(invoiceRequest.getProjectId());
        invoice.setRef(invoiceRequest.getRef());
        invoice.setDueAt(invoiceRequest.getDueAt());
        invoice.setContact(invoiceRequest.getContact());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setNote(invoiceRequest.getNote());
        invoice.setUpdatedAt(invoiceRequest.getUpdatedAt());


        try {
            Invoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
            return ResponseEntity.ok(updatedInvoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("status/{id}/")
    public ResponseEntity<Invoice> updateInvoiceStatus(@PathVariable Long id, 
                                                     @RequestBody StatusUpdate statusUpdate) {
        try {
            Invoice updatedInvoice = invoiceService.updateInvoiceStatus(id, statusUpdate.getStatus());
            return ResponseEntity.ok(updatedInvoice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("{id}/")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("invoices-by-jobs/")
    public ResponseEntity<List<Invoice>> getInvoicesByJobsAndProject(@RequestBody InvoiceFilter filter) {
        List<Invoice> invoices = invoiceService.getInvoicesByJobsAndProject(
                filter.getJobCodes(), 
                filter.getProjectCode()
        );
        return ResponseEntity.ok(invoices);
    }
}