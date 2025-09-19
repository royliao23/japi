package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Invoice;
import com.app.model.Pay;
import com.app.repository.InvoiceRepository;
import com.app.repository.PayRepository;

@Service
public class PayService {

    private final PayRepository payRepository;
    private final InvoiceRepository invoiceRepository;

    public PayService(PayRepository payRepository, InvoiceRepository invoiceRepository) {
        this.payRepository = payRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Pay savePay(Pay pay) {
        return payRepository.save(pay);
    }

    public Pay getPayById(Long code) {
        return payRepository.findById(code).orElse(null);
    }

    public List<Pay> getAllPays() {
        return payRepository.findAllByOrderByCodeDesc();
    }

    @Transactional
    public Pay updatePay(Long code, Pay pay) {
        Pay existingPay = getPayById(code);
        if (existingPay == null) return null;

        existingPay.setAmount(pay.getAmount());
        existingPay.setInvoiceId(pay.getInvoiceId());
        existingPay.setCode(pay.getCode());
        existingPay.setNote(pay.getNote());
        existingPay.setCreateAt(pay.getCreateAt());
        existingPay.setUpdatedAt(pay.getUpdatedAt());

        return payRepository.save(existingPay);
    }

    @Transactional
    public void updateInvoicePayment(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoice == null) return;

        Double totalPaid = payRepository.sumAmountByInvoiceId(invoiceId);
        if (totalPaid == null) totalPaid = 0.0;

        invoice.setPaid(totalPaid);

        // Update status
        double cost = invoice.getCost() != null ? invoice.getCost() : 0.0;
        String status = totalPaid >= cost ? "paid" : (totalPaid > 0 ? "partial paid" : "unpaid");
        invoice.setStatus(status);

        invoiceRepository.save(invoice);
    }

    @Transactional
    public void deletePay(Long payId) {
        Pay pay = getPayById(payId);
        if (pay == null) return;

        payRepository.delete(pay);
        updateInvoicePayment(pay.getInvoiceId());
    }

    public Pay updateStatus(Long id, String status) {
        Pay pay = getPayById(id);
        if (pay == null) return null;

        pay.setStatus(status);
        return payRepository.save(pay);
    }

    public List<Pay> searchPays(String keyword) {
       return payRepository.findByKeyword(keyword);
    }
}

