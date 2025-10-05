package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contractor {
    @JsonProperty("abn")
    private String abn;
    
    @JsonProperty("bsb")
    private String bsb;
    
    @JsonProperty("code")
    private Long code;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("account_no")
    private String accountNo;
    
    @JsonProperty("account_name")
    private String accountName;
    
    @JsonProperty("company_name")
    private String companyName;
    
    @JsonProperty("phone_number")
    private String phoneNumber;
    
    @JsonProperty("contact_person")
    private String contactPerson;
    
    @JsonProperty("gst_registered")
    private Integer gstRegistered; // Changed from Boolean to Integer to match your JSON (1/0)

    // Constructors
    public Contractor() {}

    // Debug constructor
    public Contractor(String companyName, String contactPerson, String phoneNumber, 
                     String email, String bsb, String accountNo, String accountName, 
                     String address, String abn, Integer gstRegistered, Long code) {
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bsb = bsb;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.address = address;
        this.abn = abn;
        this.gstRegistered = gstRegistered;
        this.code = code;
    }

    // Getters and Setters
    public String getAbn() { return abn; }
    public void setAbn(String abn) { this.abn = abn; }

    public String getBsb() { return bsb; }
    public void setBsb(String bsb) { this.bsb = bsb; }

    public Long getCode() { return code; }
    public void setCode(Long code) { this.code = code; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public Integer getGstRegistered() { return gstRegistered; }
    public void setGstRegistered(Integer gstRegistered) { this.gstRegistered = gstRegistered; }

    // Add toString for debugging
    @Override
    public String toString() {
        return "Contractor{" +
                "companyName='" + companyName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", code=" + code +
                '}';
    }
}
