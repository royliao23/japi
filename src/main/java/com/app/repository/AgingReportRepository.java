package com.app.repository;

import com.app.dto.InvoiceHistory;
import com.app.dto.Contractor;
import com.app.dto.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AgingReportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Enhanced RowMapper for Invoice with better JSON handling
    private final RowMapper<InvoiceHistory> invoiceRowMapper = new RowMapper<InvoiceHistory>() {
        @Override
        public InvoiceHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            InvoiceHistory invoice = new InvoiceHistory();
            invoice.setCode(rs.getLong("code"));
            
            // Handle date fields with null checks
            if (rs.getDate("due_at") != null) {
                invoice.setDueAt(rs.getDate("due_at").toLocalDate());
            }
            
            invoice.setCost(rs.getDouble("cost"));
            invoice.setRef(rs.getString("ref"));
            
            // Handle create_at if present (for BAS query)
            if (hasColumn(rs, "create_at") && rs.getTimestamp("create_at") != null) {
                invoice.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime()
                    .atOffset(java.time.ZoneOffset.UTC));
            }
            
            // Parse contractor JSON with better error handling
            String contractorJson = rs.getString("contractor");
            if (contractorJson != null && !contractorJson.trim().isEmpty()) {
                try {
                    invoice.setContractor(parseContractor(contractorJson));
                } catch (Exception e) {
                    System.err.println("Failed to parse contractor JSON: " + contractorJson);
                    System.err.println("Error: " + e.getMessage());
                    // Set null instead of throwing to avoid breaking the entire query
                    invoice.setContractor(null);
                }
            }
            
            // Parse pay array JSON with better error handling
            String payJson = rs.getString("pay");
            if (payJson != null && !payJson.trim().isEmpty()) {
                try {
                    invoice.setPay(parsePayArray(payJson));
                } catch (Exception e) {
                    System.err.println("Failed to parse pay JSON: " + payJson);
                    System.err.println("Error: " + e.getMessage());
                    // Set empty list instead of throwing
                    invoice.setPay(new ArrayList<>());
                }
            } else {
                invoice.setPay(new ArrayList<>());
            }
            
            return invoice;
        }
        
        // Helper method to check if column exists in result set
        private boolean hasColumn(ResultSet rs, String columnName) {
            try {
                rs.findColumn(columnName);
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
    };

    // GET all invoices
    public List<InvoiceHistory> getInvoices(int skip, int limit) {
        String sql = """
            SELECT 
                j.code,
                j.due_at,
                j.cost,
                j.ref,
                JSON_OBJECT(
                    'abn', c.abn,
                    'bsb', c.bsb,
                    'code', c.code,
                    'email', c.email,
                    'address', c.address,
                    'account_no', c.account_no,
                    'account_name', c.account_name,
                    'company_name', c.company_name,
                    'phone_number', c.phone_number,
                    'contact_person', c.contact_person,
                    'gst_registered', c.gst_registered
                ) AS contractor,
                COALESCE(
                    JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'amount', p.amount
                        )
                    ),
                    JSON_ARRAY()
                ) AS pay
            FROM jobby j
            LEFT JOIN contractor c ON j.by_id = c.code
            LEFT JOIN pay p ON j.code = p.invoice_id
            GROUP BY j.code
            ORDER BY j.code DESC
            LIMIT ? OFFSET ?
            """;
        
        return jdbcTemplate.query(sql, invoiceRowMapper, limit, skip);
    }

    // GET BAS with date range
    public List<InvoiceHistory> getBasInvoices(String start, String end, int skip, int limit) {
        String sql = """
            SELECT 
                j.code,
                j.due_at,
                j.cost,
                j.ref,
                j.create_at,
                JSON_OBJECT(
                    'abn', c.abn,
                    'bsb', c.bsb,
                    'code', c.code,
                    'email', c.email,
                    'address', c.address,
                    'account_no', c.account_no,
                    'account_name', c.account_name,
                    'company_name', c.company_name,
                    'phone_number', c.phone_number,
                    'contact_person', c.contact_person,
                    'gst_registered', c.gst_registered
                ) AS contractor,
                COALESCE(
                    JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'amount', p.amount
                        )
                    ),
                    JSON_ARRAY()
                ) AS pay
            FROM jobby j
            LEFT JOIN contractor c ON j.by_id = c.code
            LEFT JOIN pay p ON j.code = p.invoice_id
            WHERE j.due_at >= ? AND j.due_at <= ?
            GROUP BY j.code
            ORDER BY j.create_at DESC
            LIMIT ? OFFSET ?
            """;
        
        return jdbcTemplate.query(sql, invoiceRowMapper, start, end, limit, skip);
    }

    // Enhanced contractor JSON parsing with detailed error handling
    private Contractor parseContractor(String json) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            
            // Configure mapper to be more lenient with parsing
            mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            
            // Debug: print the JSON being parsed
            System.out.println("Parsing contractor JSON: " + json);
            
            Contractor contractor = mapper.readValue(json, Contractor.class);
            
            // Debug: print parsed contractor
            System.out.println("Successfully parsed contractor: " + contractor.getCompanyName());
            
            return contractor;
            
        } catch (Exception e) {
            System.err.println("JSON parsing error for contractor:");
            System.err.println("JSON: " + json);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to parse contractor JSON: " + e.getMessage(), e);
        }
    }

    // Enhanced pay array JSON parsing
    private List<Pay> parsePayArray(String json) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            com.fasterxml.jackson.core.type.TypeReference<List<Pay>> typeRef = 
                new com.fasterxml.jackson.core.type.TypeReference<List<Pay>>() {};
            
            List<Pay> pays = mapper.readValue(json, typeRef);
            
            // Filter out null amounts if any
            if (pays != null) {
                pays.removeIf(pay -> pay == null || pay.getAmount() == null);
            }
            
            return pays != null ? pays : new ArrayList<>();
            
        } catch (Exception e) {
            System.err.println("JSON parsing error for pay array:");
            System.err.println("JSON: " + json);
            System.err.println("Error: " + e.getMessage());
            // Return empty list instead of throwing
            return new ArrayList<>();
        }
    }
}