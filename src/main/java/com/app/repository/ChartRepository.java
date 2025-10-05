package com.app.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.dto.CategoryDataResponse;
import com.app.dto.JobDataResponse;
import com.app.dto.PayeeDataResponse;
import com.app.dto.ProjectCodeResponse;
import com.app.dto.ProjectDataResponse;

@Repository
public class ChartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Project Data
    public List<ProjectDataResponse> getProjectData() {
        String sql = """
            SELECT 
                p.project_name, 
                j.cost, 
                pay.amount 
            FROM jobby j
            INNER JOIN projects p ON j.project_id = p.id
            LEFT JOIN pay ON pay.invoice_id = j.code
            """;
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        Map<String, ProjectDataResponse> grouped = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String name = (String) row.get("project_name");
            Double cost = row.get("cost") != null ? ((Number) row.get("cost")).doubleValue() : 0.0;
            Double amount = row.get("amount") != null ? ((Number) row.get("amount")).doubleValue() : 0.0;
            
            grouped.putIfAbsent(name, new ProjectDataResponse(name, 0.0, 0.0));
            
            ProjectDataResponse existing = grouped.get(name);
            existing.setInvoiced(existing.getInvoiced() + cost);
            existing.setPaid(existing.getPaid() + amount);
        }
        
        return List.copyOf(grouped.values());
    }

    // Job Category Data
    public List<CategoryDataResponse> getJobCategoryData(Long projectId) {
        String sql = """
            SELECT 
                cat.name AS category_name,
                COALESCE(SUM(cost_data.total_cost), 0) AS total_cost,
                COALESCE(SUM(pay_data.total_paid), 0) AS total_paid,
                COALESCE(SUM(budget_data.total_budget), 0) AS total_budget
            FROM jm_categ cat
            INNER JOIN job j ON j.job_category_id = cat.code
            LEFT JOIN (
                SELECT job_id, SUM(cost) AS total_cost
                FROM jobby
                WHERE project_id = ?
                GROUP BY job_id
                HAVING SUM(cost) > 0
            ) AS cost_data ON j.code = cost_data.job_id
            LEFT JOIN (
                SELECT jb.job_id, SUM(p.amount) AS total_paid
                FROM jobby jb
                INNER JOIN pay p ON p.invoice_id = jb.code
                WHERE jb.project_id = ?
                GROUP BY jb.job_id
            ) AS pay_data ON j.code = pay_data.job_id
            LEFT JOIN (
                SELECT job_id, SUM(budget) AS total_budget
                FROM jobbudget
                WHERE project_id = ?
                GROUP BY job_id
            ) AS budget_data ON j.code = budget_data.job_id
            WHERE cost_data.total_cost IS NOT NULL
            GROUP BY cat.name
            ORDER BY cat.name
            """;
        
        return jdbcTemplate.query(sql, new Object[]{projectId, projectId, projectId}, 
            (rs, rowNum) -> new CategoryDataResponse(
                rs.getString("category_name"),
                rs.getDouble("total_cost"),
                rs.getDouble("total_paid"),
                rs.getDouble("total_budget")
            ));
    }

    // Job Data
    public List<JobDataResponse> getJobData(Long projectId) {
        String sql = """
            SELECT 
                j.name AS job_name,
                COALESCE(jb_data.total_cost, 0) AS total_cost,
                COALESCE(pay_data.total_paid, 0) AS total_paid,
                COALESCE(budget_data.total_budget, 0) AS total_budget
            FROM job j
            INNER JOIN (
                SELECT job_id, SUM(cost) AS total_cost
                FROM jobby
                WHERE project_id = ?
                GROUP BY job_id
            ) AS jb_data ON j.code = jb_data.job_id
            LEFT JOIN (
                SELECT jb.job_id, SUM(p.amount) AS total_paid
                FROM jobby jb
                INNER JOIN pay p ON p.invoice_id = jb.code
                WHERE jb.project_id = ?
                GROUP BY jb.job_id
            ) AS pay_data ON j.code = pay_data.job_id
            LEFT JOIN (
                SELECT job_id, SUM(budget) AS total_budget
                FROM jobbudget where project_id = ?
                GROUP BY job_id 
            ) AS budget_data ON j.code = budget_data.job_id
            ORDER BY j.name
            """;
        
        return jdbcTemplate.query(sql, new Object[]{projectId, projectId, projectId}, 
            (rs, rowNum) -> new JobDataResponse(
                rs.getString("job_name"),
                rs.getDouble("total_cost"),
                rs.getDouble("total_paid"),
                rs.getDouble("total_budget")
            ));
    }

    // Payee Data
    public List<PayeeDataResponse> getPayeeData() {
        String sql = """
            SELECT c.company_name, j.cost, p.amount
            FROM jobby j
            INNER JOIN contractor c ON j.by_id = c.code
            LEFT JOIN pay p ON p.invoice_id = j.code
            """;
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        Map<String, PayeeDataResponse> grouped = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String name = (String) row.get("company_name");
            Double cost = row.get("cost") != null ? ((Number) row.get("cost")).doubleValue() : 0.0;
            Double amount = row.get("amount") != null ? ((Number) row.get("amount")).doubleValue() : 0.0;
            
            grouped.putIfAbsent(name, new PayeeDataResponse(name, 0.0, 0.0));
            
            PayeeDataResponse existing = grouped.get(name);
            existing.setInvoiced(existing.getInvoiced() + cost);
            existing.setPaid(existing.getPaid() + amount);
        }
        
        return List.copyOf(grouped.values());
    }

    // Project Codes
    public List<ProjectCodeResponse> getProjectCodes() {
        String sql = "SELECT id as code, project_name FROM projects";
        
        return jdbcTemplate.query(sql, 
            (rs, rowNum) -> new ProjectCodeResponse(
                rs.getLong("code"),
                rs.getString("project_name")
            ));
    }
}
