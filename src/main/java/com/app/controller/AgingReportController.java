package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DateRange;
import com.app.dto.InvoiceHistory;
import com.app.service.AgingReportService;

@RestController
@RequestMapping("/high/agingreport")
public class AgingReportController {

    @Autowired
    private AgingReportService agingReportService;

    // GET /high/agingreport/
    @GetMapping("/")
    public ResponseEntity<List<InvoiceHistory>> getInvoices(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "100") int limit) {
        
        try {
            List<InvoiceHistory> invoices = agingReportService.getInvoices(skip, limit);
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // POST /high/agingreport/bas
    @PostMapping("/bas/")
    public ResponseEntity<List<InvoiceHistory>> getBas(
            @RequestBody DateRange payload,
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "100") int limit) {
        
        try {
            List<InvoiceHistory> invoices = agingReportService.getBasInvoices(payload, skip, limit);
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            System.err.println("Error fetching BAS: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
