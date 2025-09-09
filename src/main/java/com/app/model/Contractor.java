package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contractor")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    private String contactPerson;
    private String companyName;
    private String phoneNumber;
    private String email;
    private String bsb;
    private String accountNo;
    private String accountName;
    private String address;
    private String abn;
    private Boolean gstRegistered;

    // getters and setters
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBsb() { return bsb; }
    public void setBsb(String bsb) { this.bsb = bsb; }

    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAbn() { return abn; }
    public void setAbn(String abn) { this.abn = abn; }

    public Boolean getGstRegistered() { return gstRegistered; }
    public void setGstRegistered(Boolean gstRegistered) { this.gstRegistered = gstRegistered; }
}

