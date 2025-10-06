package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequest {
    private String name;
    private String email;
    private String position;
    private Double salary;
    
    @JsonProperty("super_rate")
    private Double superRate;
    
    private String role;
    private String address;
    
    @JsonProperty("employment_type")
    private String employmentType;
    
    private String bsb;
    
    @JsonProperty("account_name")
    private String accountName;
    
    @JsonProperty("account_no")
    private String accountNo;
    
    @JsonProperty("bank_name")
    private String bankName;
    
    private String mobile;
    private Long department;
    
    @JsonProperty("first_name")
    private String firstName;
    
    @JsonProperty("last_name")
    private String lastName;
    
    private String contact;
    
    @JsonProperty("super_company_name")
    private String superCompanyName;
    
    @JsonProperty("super_usi")
    private String superUsi;
    
    @JsonProperty("super_fund_abn")
    private String superFundAbn;
    
    @JsonProperty("super_account_name")
    private String superAccountName;
    
    @JsonProperty("super_member_no")
    private String superMemberNo;

    // Constructors
    public EmployeeRequest() {}

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public Double getSuperRate() { return superRate; }
    public void setSuperRate(Double superRate) { this.superRate = superRate; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public String getBsb() { return bsb; }
    public void setBsb(String bsb) { this.bsb = bsb; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public Long getDepartment() { return department; }
    public void setDepartment(Long department) { this.department = department; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getSuperCompanyName() { return superCompanyName; }
    public void setSuperCompanyName(String superCompanyName) { this.superCompanyName = superCompanyName; }

    public String getSuperUsi() { return superUsi; }
    public void setSuperUsi(String superUsi) { this.superUsi = superUsi; }

    public String getSuperFundAbn() { return superFundAbn; }
    public void setSuperFundAbn(String superFundAbn) { this.superFundAbn = superFundAbn; }

    public String getSuperAccountName() { return superAccountName; }
    public void setSuperAccountName(String superAccountName) { this.superAccountName = superAccountName; }

    public String getSuperMemberNo() { return superMemberNo; }
    public void setSuperMemberNo(String superMemberNo) { this.superMemberNo = superMemberNo; }
}