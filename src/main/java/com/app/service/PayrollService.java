package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.EmployeeResponse;
import com.app.dto.PayrollRequest;
import com.app.dto.PayrollResponse;
import com.app.repository.PayrollRepository;

@Service
@Transactional
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // READ ALL (paginated with employee info)
    public List<PayrollResponse> getAllPayrolls(int skip, int limit) {
        String sql = """
            SELECT 
                p.*,
                JSON_OBJECT(
                    'id', e.id,
                    'name', e.name,
                    'email', e.email,
                    'position', e.position,
                    'salary', e.salary,
                    'super_rate', e.super_rate,
                    'role', e.role,
                    'address', e.address,
                    'employment_type', e.employment_type,
                    'bsb', e.bsb,
                    'account_name', e.account_name,
                    'account_no', e.account_no,
                    'bank_name', e.bank_name,
                    'mobile', e.mobile,
                    'department', e.department,
                    'first_name', e.first_name,
                    'last_name', e.last_name,
                    'contact', e.contact,
                    'super_company_name', e.super_company_name,
                    'super_usi', e.super_usi,
                    'super_fund_abn', e.super_fund_abn,
                    'super_account_name', e.super_account_name,
                    'super_member_no', e.super_member_no
                ) AS employee
            FROM payroll p
            LEFT JOIN employee e ON p.employee_id = e.id
            ORDER BY p.id DESC
            LIMIT ? OFFSET ?
            """;
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, limit, skip);
        
        return rows.stream()
                .map(this::convertRowToPayrollResponse)
                .collect(Collectors.toList());
    }

    // CREATE
    public PayrollResponse createPayroll(PayrollRequest request) {
        String insertSql = """
            INSERT INTO payroll (
                employee_id, period, gross_pay, tax, `super`, net_pay,
                base_hour, overtime_15, overtime_20, holiday_pay,
                bonus, other_pay, from_date, to_date, note
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        
        jdbcTemplate.update(insertSql,
            request.getEmployeeId(),
            request.getPeriod(),
            request.getGrossPay(),
            request.getTax(),
            request.getSuperAmount(),
            request.getNetPay(),
            request.getBaseHour(),
            request.getOvertime15(),
            request.getOvertime20(),
            request.getHolidayPay(),
            request.getBonus(),
            request.getOtherPay(),
            request.getFromDate(),
            request.getToDate(),
            request.getNote()
        );
        
        // Get the last inserted payroll
        List<PayrollResponse> results = getAllPayrolls(0, 1);
        return results.get(0);
    }

    // READ ONE
    public PayrollResponse getPayrollById(Long payrollId) {
        String sql = """
            SELECT 
                p.*,
                JSON_OBJECT(
                    'id', e.id,
                    'name', e.name,
                    'email', e.email,
                    'position', e.position,
                    'salary', e.salary,
                    'super_rate', e.super_rate,
                    'role', e.role,
                    'address', e.address,
                    'employment_type', e.employment_type,
                    'bsb', e.bsb,
                    'account_name', e.account_name,
                    'account_no', e.account_no,
                    'bank_name', e.bank_name,
                    'mobile', e.mobile,
                    'department', e.department,
                    'first_name', e.first_name,
                    'last_name', e.last_name,
                    'contact', e.contact,
                    'super_company_name', e.super_company_name,
                    'super_usi', e.super_usi,
                    'super_fund_abn', e.super_fund_abn,
                    'super_account_name', e.super_account_name,
                    'super_member_no', e.super_member_no
                ) AS employee
            FROM payroll p
            LEFT JOIN employee e ON p.employee_id = e.id
            WHERE p.id = ?
            """;
        
        try {
            Map<String, Object> row = jdbcTemplate.queryForMap(sql, payrollId);
            return convertRowToPayrollResponse(row);
        } catch (Exception e) {
            throw new RuntimeException("Payroll not found with id: " + payrollId);
        }
    }

    // UPDATE
    public PayrollResponse updatePayroll(Long payrollId, PayrollRequest request) {
        String updateSql = """
            UPDATE payroll
            SET 
                employee_id = ?, period = ?, gross_pay = ?,
                tax = ?, `super` = ?, net_pay = ?,
                base_hour = ?, overtime_15 = ?,
                overtime_20 = ?, holiday_pay = ?,
                bonus = ?, other_pay = ?, from_date = ?,
                to_date = ?, note = ?
            WHERE id = ?
            """;
        
        int affectedRows = jdbcTemplate.update(updateSql,
            request.getEmployeeId(),
            request.getPeriod(),
            request.getGrossPay(),
            request.getTax(),
            request.getSuperAmount(),
            request.getNetPay(),
            request.getBaseHour(),
            request.getOvertime15(),
            request.getOvertime20(),
            request.getHolidayPay(),
            request.getBonus(),
            request.getOtherPay(),
            request.getFromDate(),
            request.getToDate(),
            request.getNote(),
            payrollId
        );
        
        if (affectedRows == 0) {
            throw new RuntimeException("Payroll not found with id: " + payrollId);
        }
        
        return getPayrollById(payrollId);
    }

    // DELETE
    public void deletePayroll(Long payrollId) {
        String deleteSql = "DELETE FROM payroll WHERE id = ?";
        int affectedRows = jdbcTemplate.update(deleteSql, payrollId);
        
        if (affectedRows == 0) {
            throw new RuntimeException("Payroll not found with id: " + payrollId);
        }
    }

    // Helper method to convert database row to PayrollResponse
    private PayrollResponse convertRowToPayrollResponse(Map<String, Object> row) {
        PayrollResponse response = new PayrollResponse();
        
        // Map payroll fields
        response.setId(getLong(row, "id"));
        response.setEmployeeId(getLong(row, "employee_id"));
        response.setPeriod(getString(row, "period"));
        response.setGrossPay(getDouble(row, "gross_pay"));
        response.setTax(getDouble(row, "tax"));
        response.setSuperAmount(getDouble(row, "super"));
        response.setNetPay(getDouble(row, "net_pay"));
        response.setBaseHour(getDouble(row, "base_hour"));
        response.setOvertime15(getDouble(row, "overtime_15"));
        response.setOvertime20(getDouble(row, "overtime_20"));
        response.setHolidayPay(getDouble(row, "holiday_pay"));
        response.setBonus(getDouble(row, "bonus"));
        response.setOtherPay(getDouble(row, "other_pay"));
        response.setFromDate(getLocalDate(row, "from_date"));
        response.setToDate(getLocalDate(row, "to_date"));
        response.setNote(getString(row, "note"));
        
        // Parse employee JSON
        String employeeJson = getString(row, "employee");
        if (employeeJson != null && !employeeJson.trim().isEmpty()) {
            try {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                EmployeeResponse employee = mapper.readValue(employeeJson, EmployeeResponse.class);
                response.setEmployee(employee);
            } catch (Exception e) {
                System.err.println("Failed to parse employee JSON: " + employeeJson);
                System.err.println("Error: " + e.getMessage());
                response.setEmployee(null);
            }
        } else {
            response.setEmployee(null);
        }
        
        return response;
    }

    // Helper methods to safely extract values
    private String getString(Map<String, Object> row, String key) {
        Object value = row.get(key);
        return value != null ? value.toString() : null;
    }

    private Long getLong(Map<String, Object> row, String key) {
        Object value = row.get(key);
        if (value == null) return null;
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double getDouble(Map<String, Object> row, String key) {
        Object value = row.get(key);
        if (value == null) return null;
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private LocalDate getLocalDate(Map<String, Object> row, String key) {
        Object value = row.get(key);
        if (value == null) return null;
        if (value instanceof java.sql.Date) {
            return ((java.sql.Date) value).toLocalDate();
        }
        try {
            return java.time.LocalDate.parse(value.toString());
        } catch (Exception e) {
            return null;
        }
    }
}
