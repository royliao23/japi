package com.app.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.dto.EnhancedPayResponse;
import com.app.dto.EnhancedPaymentResponse;
import com.app.model.Invoice;
import com.app.model.Pay;
import com.app.repository.InvoiceRepository;
import com.app.repository.PayRepository;
import com.app.repository.PayRepositoryNative;

@Service
public class PayService {

    private final PayRepository payRepository;
    private final InvoiceRepository invoiceRepository;
    private final PayRepositoryNative payRepositoryNative;

    public PayService(PayRepository payRepository, InvoiceRepository invoiceRepository, PayRepositoryNative payRepositoryNative) {
        this.payRepository = payRepository;
        this.invoiceRepository = invoiceRepository;
        this.payRepositoryNative = payRepositoryNative;
    }

    

    public EnhancedPayResponse getPayWithInvoice(Long code) {
        return (EnhancedPayResponse) payRepositoryNative.getPayWithInvoice(code);
    }

    // @Transactional
    // public Pay savePay(Pay pay) {
    //     return payRepository.save(pay);
    // }

    public Pay getPayById(Long code) {
        return payRepository.findById(code).orElse(null);
    }

    public List<Pay> getAllPays() {
        return payRepository.findAllByOrderByCodeDesc();
    }
    public List<EnhancedPaymentResponse> getAllPaymentsWithInvoiceDetails() {
        List<Pay> payments = payRepository.findAllByOrderByCodeDesc();
        List<EnhancedPaymentResponse> enhancedPayments = new ArrayList<>();

        for (Pay payment : payments) {
            EnhancedPaymentResponse enhanced = convertToEnhancedResponse(payment);
            
            // Get invoice details
            Invoice invoice = invoiceRepository.findById(payment.getInvoiceId()).orElse(null);
            if (invoice != null) {
                enhanced.setJobby(convertInvoiceToMap(invoice));
            }
            
            enhancedPayments.add(enhanced);
        }
        
        return enhancedPayments;
    }

   
    // @Transactional
    // public Pay updatePayment(Long payId, Pay paymentDetails) {
    //     Pay payment = payRepository.findById(payId)
    //             .orElseThrow(() -> new RuntimeException("Payment not found"));
        
    //     // Check if payment is older than 30 days
    //     if (payment.getCreateAt().plusDays(30).isBefore(OffsetDateTime.now())) {
    //         throw new RuntimeException("Cannot edit payment more than 30 days after creation");
    //     }
        
    //     // Update payment fields
    //     payment.setAmount(paymentDetails.getAmount());
    //     payment.setPayVia(paymentDetails.getPayVia());
    //     payment.setInvoiceId(paymentDetails.getInvoiceId());
    //     payment.setSupplyInvoice(paymentDetails.getSupplyInvoice());
    //     payment.setApprovedBy(paymentDetails.getApprovedBy());
    //     payment.setNote(paymentDetails.getNote());
    //     payment.setUpdatedAt(OffsetDateTime.now());

    //     Pay updatedPayment = payRepository.save(payment);
    //     updateInvoicePayment(payment.getInvoiceId());
        
    //     return updatedPayment;
    // }

    // private void updateInvoicePayment(Long invoiceId) {
    //     Double totalPaid = payRepository.getTotalPaidByInvoiceId(invoiceId);
    //     Invoice invoice = invoiceRepository.findById(invoiceId)
    //             .orElseThrow(() -> new RuntimeException("Invoice not found"));
        
    //     Double cost = invoice.getCost() != null ? invoice.getCost() : 0.0;
    //     String status;
        
    //     if (totalPaid >= cost) {
    //         status = "paid";
    //     } else if (totalPaid > 0) {
    //         status = "partial paid";
    //     } else {
    //         status = "unpaid";
    //     }
        
    //     invoice.setPaid(totalPaid);
    //     invoice.setStatus(status);
    //     invoiceRepository.save(invoice);
    // }

    // @Transactional
    // public void deletePay(Long payId) {
    //     Pay pay = getPayById(payId);
    //     if (pay == null) return;

    //     payRepository.delete(pay);
    //     updateInvoicePayment(pay.getInvoiceId());
    // }

    public Pay updateStatus(Long id, String status) {
        Pay pay = getPayById(id);
        if (pay == null) return null;

        pay.setStatus(status);
        return payRepository.save(pay);
    }

    public List<Pay> searchPays(String keyword) {
       return payRepository.findByKeyword(keyword);
    }
    private EnhancedPaymentResponse convertToEnhancedResponse(Pay payment) {
        EnhancedPaymentResponse response = new EnhancedPaymentResponse();
        response.setCode(payment.getCode());
        response.setAmount(payment.getAmount());
        response.setPayVia(payment.getPayVia());
        response.setInvoiceId(payment.getInvoiceId());
        response.setSupplyInvoice(payment.getSupplyInvoice());
        response.setApprovedBy(payment.getApprovedBy());
        response.setNote(payment.getNote());
        response.setCreateAt(payment.getCreateAt() != null ? payment.getCreateAt().toString() : null);
        response.setUpdatedAt(payment.getUpdatedAt() != null ? payment.getUpdatedAt().toString() : null);
        return response;
    }
    
    private Map<String, Object> convertInvoiceToMap(Invoice invoice) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", invoice.getCode());
        map.put("due_at", invoice.getDueAt());
        map.put("cost", invoice.getCost());
        map.put("status", invoice.getStatus());
        map.put("by_id", invoice.getById());
        map.put("project_id", invoice.getProjectId());
        map.put("job_id", invoice.getJobId());
        return map;
    }
    // CREATE pay and update invoice
    public Pay createPayAndUpdateInvoice(Pay pay) {
        // Save the payment first
        Pay savedPay = payRepository.save(pay);
        
        // Update the associated invoice
        updateInvoicePayment(pay.getInvoiceId());
        
        return savedPay;
    }

    // UPDATE pay and update invoice
    public Pay updatePaymentAndInvoice(Long payId, Pay paymentDetails) {
        Pay existingPay = payRepository.findById(payId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + payId));
        
        // Check if payment is older than 30 days
        if (existingPay.getCreateAt().plusDays(30).isBefore(OffsetDateTime.now())) {
            throw new RuntimeException("Cannot edit payment more than 30 days after creation");
        }
        
        // Store old invoice ID for cleanup if invoice changed
        Long oldInvoiceId = existingPay.getInvoiceId();
        
        // Update payment fields
        existingPay.setAmount(paymentDetails.getAmount());
        existingPay.setPayVia(paymentDetails.getPayVia());
        existingPay.setInvoiceId(paymentDetails.getInvoiceId());
        existingPay.setSupplyInvoice(paymentDetails.getSupplyInvoice());
        existingPay.setApprovedBy(paymentDetails.getApprovedBy());
        existingPay.setNote(paymentDetails.getNote());
        existingPay.setUpdatedAt(OffsetDateTime.now());

        Pay updatedPayment = payRepository.save(existingPay);
        
        // Update both old and new invoices if invoice ID changed
        if (oldInvoiceId != null && !oldInvoiceId.equals(paymentDetails.getInvoiceId())) {
            updateInvoicePayment(oldInvoiceId); // Update old invoice
        }
        updateInvoicePayment(paymentDetails.getInvoiceId()); // Update new invoice
        
        return updatedPayment;
    }

    // DELETE pay and update invoice
    public void deletePayAndUpdateInvoice(Long payId) {
        Pay pay = payRepository.findById(payId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + payId));
        
        Long invoiceId = pay.getInvoiceId();
        
        // Delete the payment
        payRepository.deleteById(payId);
        
        // Update the associated invoice
        updateInvoicePayment(invoiceId);
    }

    // Method to update invoice paid amount and status
    private void updateInvoicePayment(Long invoiceId) {
        if (invoiceId == null) return;
        
        // Get the invoice
        Invoice invoice = invoiceRepository.findByCode(invoiceId);
        if (invoice == null) return;
        
        // Calculate total paid amount for this invoice
        Double totalPaid = payRepository.getTotalPaidByInvoiceId(invoiceId);
        if (totalPaid == null) {
            totalPaid = 0.0;
        }
        
        // Update invoice paid amount
        invoice.setPaid(totalPaid);
        
        // Update invoice status based on paid amount
        Double cost = invoice.getCost() != null ? invoice.getCost() : 0.0;
        
        if (totalPaid == 0) {
            invoice.setStatus("unpaid");
        } else if (totalPaid >= cost) {
            invoice.setStatus("paid");
        } else if (totalPaid > 0) {
            invoice.setStatus("partial paid");
        } else {
            invoice.setStatus("unpaid");
        }
        
        invoice.setUpdatedAt(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }

    // Keep your existing methods...
    public Pay savePay(Pay pay) {
        return payRepository.save(pay);
    }
    
    public Pay updatePayment(Long payId, Pay paymentDetails) {
        // Your existing implementation
        return updatePaymentAndInvoice(payId, paymentDetails);
    }
    
    public void deletePay(Long id) {
        deletePayAndUpdateInvoice(id);
    }
}

