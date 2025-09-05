package com.example.dto;

public class CompanyUpdate {
    private String companyName;
    private String address;
    private String abn;
    private String director;
    private String tfn;
    private String acn;
    private String phone;
    private String email;

    // Default constructor
    public CompanyUpdate() {}

    // Parameterized constructor (optional)
    public CompanyUpdate(String companyName, String address, String abn, String director, 
                        String tfn, String acn, String phone, String email) {
        this.companyName = companyName;
        this.address = address;
        this.abn = abn;
        this.director = director;
        this.tfn = tfn;
        this.acn = acn;
        this.phone = phone;
        this.email = email;
    }

    // Getter methods
    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getAbn() {
        return abn;
    }

    public String getDirector() {
        return director;
    }

    public String getTfn() {
        return tfn;
    }

    public String getAcn() {
        return acn;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setter methods (optional, but good practice)
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTfn(String tfn) {
        this.tfn = tfn;
    }

    public void setAcn(String acn) {
        this.acn = acn;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}