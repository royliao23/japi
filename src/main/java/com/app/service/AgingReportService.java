package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.DateRange;
import com.app.dto.InvoiceHistory;
import com.app.repository.AgingReportRepository;

@Service
public class AgingReportService {

    @Autowired
    private AgingReportRepository agingReportRepository;

    public List<InvoiceHistory> getInvoices(int skip, int limit) {
        return agingReportRepository.getInvoices(skip, limit);
    }

    public List<InvoiceHistory> getBasInvoices(DateRange dateRange, int skip, int limit) {
        System.out.printf("Fetching BAS from %s to %s with skip=%d and limit=%d%n", 
            dateRange.getStart(), dateRange.getEnd(), skip, limit);
        
        return agingReportRepository.getBasInvoices(
            dateRange.getStart().toString(), 
            dateRange.getEnd().toString(), 
            skip, 
            limit
        );
    }
}
