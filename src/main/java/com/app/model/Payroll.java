package com.app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    private String period;
    
    @Column(name = "gross_pay")
    private Double grossPay;
    
    private Double tax;
    
    @Column(name = "super_amount") // Using different name to avoid keyword conflict
    private Double superAmount;
    
    @Column(name = "net_pay")
    private Double netPay;
    
    @Column(name = "base_hour")
    private Double baseHour;
    
    @Column(name = "overtime_15")
    private Double overtime15;
    
    @Column(name = "overtime_20")
    private Double overtime20;
    
    @Column(name = "holiday_pay")
    private Double holidayPay;
    
    private Double bonus;
    
    @Column(name = "other_pay")
    private Double otherPay;
    
    @Column(name = "from_date")
    private LocalDate fromDate;
    
    @Column(name = "to_date")
    private LocalDate toDate;
    
    private String note;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    // Constructors
    public Payroll() {}

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

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
