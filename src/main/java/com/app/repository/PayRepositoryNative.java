package com.app.repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.dto.EnhancedPayResponse;
import com.app.dto.JobbyResponse;

@Repository
public class PayRepositoryNative {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public EnhancedPayResponse getPayWithInvoice(Long code) {
        String sql = """
            SELECT p.code, p.amount, p.pay_via, p.invoice_id, p.supply_invoice, 
                   p.approved_by, p.note, p.create_at, p.updated_at,
                   j.code as jobby_code, j.due_at, j.cost, j.status, 
                   j.by_id, j.project_id, j.job_id
            FROM pay p
            LEFT JOIN jobby j ON p.invoice_id = j.code
            WHERE p.code = ?
            """;
        
        return jdbcTemplate.queryForObject(sql, new Object[]{code}, (rs, rowNum) -> {
            // Create JobbyResponse
            JobbyResponse jobby = new JobbyResponse(
                rs.getLong("jobby_code"),
                rs.getDate("due_at") != null ? rs.getDate("due_at").toLocalDate() : null,
                rs.getDouble("cost"),
                rs.getString("status"),
                rs.getLong("by_id"),
                rs.getLong("project_id"),
                rs.getLong("job_id")
            );
            
            // Convert create_at timestamp to LocalDate
            LocalDate createAt = rs.getTimestamp("create_at") != null ? 
                rs.getTimestamp("create_at").toLocalDateTime().toLocalDate() : null;
            
            // Convert updated_at to OffsetDateTime
            OffsetDateTime updatedAt = rs.getTimestamp("updated_at") != null ?
                rs.getTimestamp("updated_at").toLocalDateTime().atOffset(ZoneOffset.UTC) : null;
            
            return new EnhancedPayResponse(
                rs.getLong("code"),
                rs.getDouble("amount"),
                rs.getString("pay_via"),
                rs.getLong("invoice_id"),
                rs.getString("supply_invoice"),
                rs.getString("approved_by"),
                rs.getString("note"),
                createAt,
                updatedAt,
                jobby
            );
        });
    }
}