package com.app.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DepartmentInfo;
import com.app.dto.EmployeeRequest;
import com.app.dto.EmployeeResponse;
import com.app.dto.EmployeeUpdateRequest;
import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE - with ALL fields
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        
        // Set ALL fields from the request
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
        employee.setSuperRate(request.getSuperRate());
        employee.setRole(request.getRole());
        employee.setAddress(request.getAddress());
        employee.setEmploymentType(request.getEmploymentType());
        employee.setBsb(request.getBsb());
        employee.setAccountName(request.getAccountName());
        employee.setAccountNo(request.getAccountNo());
        employee.setBankName(request.getBankName());
        employee.setMobile(request.getMobile());
        employee.setDepartment(request.getDepartment());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setContact(request.getContact());
        employee.setSuperCompanyName(request.getSuperCompanyName());
        employee.setSuperUsi(request.getSuperUsi());
        employee.setSuperFundAbn(request.getSuperFundAbn());
        employee.setSuperAccountName(request.getSuperAccountName());
        employee.setSuperMemberNo(request.getSuperMemberNo());

        Employee savedEmployee = employeeRepository.save(employee);
        
        // Get the created employee with department info
        return getEmployeeWithDepartmentInfo(savedEmployee.getId());
    }

    // READ ALL (paginated with department info)
    public List<EmployeeResponse> getAllEmployees(int skip, int limit) {
        String sql = """
            SELECT
                e.*,
                JSON_OBJECT(
                    'id',            d.id,
                    'department_name', d.department_name,
                    'description',     d.description,
                    'manager',         d.manager
                ) AS department_info
            FROM employee e
            LEFT JOIN department d ON e.department = d.id
            ORDER BY e.id DESC
            LIMIT ? OFFSET ?
            """;
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, limit, skip);
        
        return rows.stream()
                .map(this::convertRowToEmployeeResponse)
                .collect(Collectors.toList());
    }

    // READ ALL (non-paginated with department info)
    public List<EmployeeResponse> getAllEmployees() {
        String sql = """
            SELECT e.*, 
                JSON_OBJECT('id', d.id, 'department_name', d.department_name, 
                           'description', d.description, 'manager', d.manager) AS department_info
            FROM employee e
            LEFT JOIN department d ON e.department = d.id
            ORDER BY e.id DESC
            """;
        
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        
        return rows.stream()
                .map(this::convertRowToEmployeeResponse)
                .collect(Collectors.toList());
    }

    // READ ONE
    public EmployeeResponse getEmployeeById(Long employeeId) {
        return getEmployeeWithDepartmentInfo(employeeId);
    }

    // UPDATE - with ALL fields
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateRequest request) {
        // Check if any fields are provided for update
        if (!request.hasUpdates()) {
            throw new RuntimeException("No data provided for update");
        }

        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        // Update ALL provided fields
        if (request.getName() != null) {
            existingEmployee.setName(request.getName());
        }
        if (request.getEmail() != null) {
            existingEmployee.setEmail(request.getEmail());
        }
        if (request.getPosition() != null) {
            existingEmployee.setPosition(request.getPosition());
        }
        if (request.getSalary() != null) {
            existingEmployee.setSalary(request.getSalary());
        }
        if (request.getSuperRate() != null) {
            existingEmployee.setSuperRate(request.getSuperRate());
        }
        if (request.getRole() != null) {
            existingEmployee.setRole(request.getRole());
        }
        if (request.getAddress() != null) {
            existingEmployee.setAddress(request.getAddress());
        }
        if (request.getEmploymentType() != null) {
            existingEmployee.setEmploymentType(request.getEmploymentType());
        }
        if (request.getBsb() != null) {
            existingEmployee.setBsb(request.getBsb());
        }
        if (request.getAccountName() != null) {
            existingEmployee.setAccountName(request.getAccountName());
        }
        if (request.getAccountNo() != null) {
            existingEmployee.setAccountNo(request.getAccountNo());
        }
        if (request.getBankName() != null) {
            existingEmployee.setBankName(request.getBankName());
        }
        if (request.getMobile() != null) {
            existingEmployee.setMobile(request.getMobile());
        }
        if (request.getDepartment() != null) {
            existingEmployee.setDepartment(request.getDepartment());
        }
        if (request.getFirstName() != null) {
            existingEmployee.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            existingEmployee.setLastName(request.getLastName());
        }
        if (request.getContact() != null) {
            existingEmployee.setContact(request.getContact());
        }
        if (request.getSuperCompanyName() != null) {
            existingEmployee.setSuperCompanyName(request.getSuperCompanyName());
        }
        if (request.getSuperUsi() != null) {
            existingEmployee.setSuperUsi(request.getSuperUsi());
        }
        if (request.getSuperFundAbn() != null) {
            existingEmployee.setSuperFundAbn(request.getSuperFundAbn());
        }
        if (request.getSuperAccountName() != null) {
            existingEmployee.setSuperAccountName(request.getSuperAccountName());
        }
        if (request.getSuperMemberNo() != null) {
            existingEmployee.setSuperMemberNo(request.getSuperMemberNo());
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        
        // Return updated employee with department info
        return getEmployeeWithDepartmentInfo(employeeId);
    }

    // DELETE
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }

    // Helper method to get employee with department info
    private EmployeeResponse getEmployeeWithDepartmentInfo(Long employeeId) {
        String sql = """
            SELECT
                e.*,
                JSON_OBJECT(
                    'id',            d.id,
                    'department_name', d.department_name,
                    'description',     d.description,
                    'manager',         d.manager
                ) AS department_info
            FROM employee e
            LEFT JOIN department d ON e.department = d.id
            WHERE e.id = ?
            """;
        
        try {
            Map<String, Object> row = jdbcTemplate.queryForMap(sql, employeeId);
            return convertRowToEmployeeResponse(row);
        } catch (Exception e) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }

    private EmployeeResponse convertRowToEmployeeResponse(Map<String, Object> row) {
        EmployeeResponse response = new EmployeeResponse();
        
        // Map ALL fields from the database row
        response.setId(getLong(row, "id"));
        response.setName(getString(row, "name"));
        response.setEmail(getString(row, "email"));
        response.setPosition(getString(row, "position"));
        response.setSalary(getDouble(row, "salary"));
        response.setRole(getString(row, "role"));
        response.setAddress(getString(row, "address"));
        response.setBsb(getString(row, "bsb"));
        response.setMobile(getString(row, "mobile"));
        response.setDepartment(getLong(row, "department"));
        response.setContact(getString(row, "contact"));
        response.setSuperRate(getDouble(row, "super_rate"));
        response.setEmploymentType(getString(row, "employment_type"));
        response.setAccountName(getString(row, "account_name"));
        response.setAccountNo(getString(row, "account_no"));
        response.setBankName(getString(row, "bank_name"));
        response.setFirstName(getString(row, "first_name"));
        response.setLastName(getString(row, "last_name"));
        response.setSuperCompanyName(getString(row, "super_company_name"));
        response.setSuperUsi(getString(row, "super_usi"));
        response.setSuperFundAbn(getString(row, "super_fund_abn"));
        response.setSuperAccountName(getString(row, "super_account_name"));
        response.setSuperMemberNo(getString(row, "super_member_no"));
        
        // Parse department_info JSON
        String departmentInfoJson = getString(row, "department_info");
        if (departmentInfoJson != null && !departmentInfoJson.trim().isEmpty()) {
            try {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                DepartmentInfo departmentInfo = mapper.readValue(departmentInfoJson, DepartmentInfo.class);
                response.setDepartmentInfo(departmentInfo);
            } catch (Exception e) {
                System.err.println("Failed to parse department_info JSON: " + departmentInfoJson);
                System.err.println("Error: " + e.getMessage());
                response.setDepartmentInfo(null);
            }
        } else {
            response.setDepartmentInfo(null);
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
}