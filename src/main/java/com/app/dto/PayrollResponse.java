package com.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayrollResponse {
    private Long id;
    
    @JsonProperty("employee_id")
    private Long employeeId;
    
    private String period;
    
    @JsonProperty("gross_pay")
    private Double grossPay;
    
    private Double tax;
    
    @JsonProperty("super")
    private Double superAmount;
    
    @JsonProperty("net_pay")
    private Double netPay;
    
    @JsonProperty("base_hour")
    private Double baseHour;
    
    @JsonProperty("overtime_15")
    private Double overtime15;
    
    @JsonProperty("overtime_20")
    private Double overtime20;
    
    @JsonProperty("holiday_pay")
    private Double holidayPay;
    
    private Double bonus;
    
    @JsonProperty("other_pay")
    private Double otherPay;
    
    @JsonProperty("from_date")
    private LocalDate fromDate;
    
    @JsonProperty("to_date")
    private LocalDate toDate;
    
    private String note;
    
    private EmployeeResponse employee;

    // Constructors
    public PayrollResponse() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public Double getGrossPay() { return grossPay; }
    public void setGrossPay(Double grossPay) { this.grossPay = grossPay; }

    public Double getTax() { return tax; }
    public void setTax(Double tax) { this.tax = tax; }

    public Double getSuperAmount() { return superAmount; }
    public void setSuperAmount(Double superAmount) { this.superAmount = superAmount; }

    public Double getNetPay() { return netPay; }
    public void setNetPay(Double netPay) { this.netPay = netPay; }

    public Double getBaseHour() { return baseHour; }
    public void setBaseHour(Double baseHour) { this.baseHour = baseHour; }

    public Double getOvertime15() { return overtime15; }
    public void setOvertime15(Double overtime15) { this.overtime15 = overtime15; }

    public Double getOvertime20() { return overtime20; }
    public void setOvertime20(Double overtime20) { this.overtime20 = overtime20; }

    public Double getHolidayPay() { return holidayPay; }
    public void setHolidayPay(Double holidayPay) { this.holidayPay = holidayPay; }

    public Double getBonus() { return bonus; }
    public void setBonus(Double bonus) { this.bonus = bonus; }

    public Double getOtherPay() { return otherPay; }
    public void setOtherPay(Double otherPay) { this.otherPay = otherPay; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public EmployeeResponse getEmployee() { return employee; }
    public void setEmployee(EmployeeResponse employee) { this.employee = employee; }
}
