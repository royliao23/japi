package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EnhancedPayResponse;
import com.app.dto.EnhancedPaymentResponse;
import com.app.dto.PayRequest;
import com.app.dto.StatusUpdate;
import com.app.model.Pay;
import com.app.service.PayService;

@RestController
@RequestMapping("/high/pay/")
public class PayController {

    private final PayService payService;

    // Constructor injection
    public PayController(PayService payService) {
        this.payService = payService;
    }

   @PostMapping
public ResponseEntity<Pay> createPay(@RequestBody PayRequest payRequest) {
    try {
        Pay pay = new Pay();
        pay.setAmount(payRequest.getAmount());
        pay.setPayVia(payRequest.getPayVia());
        pay.setInvoiceId(payRequest.getInvoiceId());
        // Don't set code for new entities - it's auto-generated
        // pay.setCode(payRequest.getCode()); 
        pay.setSupplyInvoice(payRequest.getSupplyInvoice());
        pay.setApprovedBy(payRequest.getApprovedBy());
        pay.setNote(payRequest.getNote());
        
        // Let JPA handle timestamps or set them if provided
        if (payRequest.getCreateAt() != null) {
            pay.setCreateAt(payRequest.getCreateAt());
        }
        if (payRequest.getUpdatedAt() != null) {
            pay.setUpdatedAt(payRequest.getUpdatedAt());
        }

        Pay createdPay = payService.savePay(pay);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPay);
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }
}


    // ✅ Get all pays
    @GetMapping
    public ResponseEntity<List<EnhancedPaymentResponse>> getAllPayments() {
        List<EnhancedPaymentResponse> payments = payService.getAllPaymentsWithInvoiceDetails();
        return ResponseEntity.ok(payments);
    }

    // ✅ Get pay by ID
    @GetMapping("old/{id}/")
    public ResponseEntity<Pay> getPayById(@PathVariable Long id) {
        Pay pay = payService.getPayById(id);
        return pay != null ? ResponseEntity.ok(pay) : ResponseEntity.notFound().build();
    }
    @GetMapping("{code}/")
    public ResponseEntity<EnhancedPayResponse> getPay(@PathVariable Long code) {
        EnhancedPayResponse response = payService.getPayWithInvoice(code);
        return ResponseEntity.ok(response);
    }
    // ✅ Update pay
   @PutMapping("{payId}/")
    public ResponseEntity<?> updatePayment(@PathVariable Long payId, @RequestBody PayRequest paymentRequest) {
        try {
            Pay payment = new Pay();
            payment.setAmount(paymentRequest.getAmount());
            payment.setPayVia(paymentRequest.getPayVia());
            payment.setInvoiceId(paymentRequest.getInvoiceId());
            payment.setSupplyInvoice(paymentRequest.getSupplyInvoice());
            payment.setApprovedBy(paymentRequest.getApprovedBy());
            payment.setNote(paymentRequest.getNote());
            
            Pay updatedPayment = payService.updatePayment(payId, payment);
            return ResponseEntity.ok().body(updatedPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Delete pay
    @DeleteMapping("{id}/")
    public ResponseEntity<Void> deletePay(@PathVariable Long id) {
        payService.deletePay(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Update only status
    @PatchMapping("{id}/status/")
    public ResponseEntity<Pay> updatePayStatus(@PathVariable Long id,
                                               @RequestBody StatusUpdate statusUpdate) {
        Pay updatedPay = payService.updateStatus(id, statusUpdate.getStatus());
        return updatedPay != null ? ResponseEntity.ok(updatedPay) : ResponseEntity.notFound().build();
    }

    // ✅ Filter pays (e.g., by invoiceId, status, date ranges, etc.)
    // @PostMapping("/filter")
    // public ResponseEntity<List<Pay>> filterPays(@RequestBody PayFilter filter) {
    //     return ResponseEntity.ok(payService.filterPays(filter));
    // }

    // ✅ Search pays by reference or note
    @GetMapping("search/")
    public ResponseEntity<List<Pay>> searchPays(@RequestParam String keyword) {
        return ResponseEntity.ok(payService.searchPays(keyword));
    }
}
