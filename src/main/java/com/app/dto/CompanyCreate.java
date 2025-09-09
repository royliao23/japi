package com.app.dto;

public class CompanyCreate {
    private String companyName;
    private String address;
    private String abn;
    private String director;
    private String tfn;
    private String acn;
    private String phone;
    private String email;

    // Getters and setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getAbn() { return abn; }
    public void setAbn(String abn) { this.abn = abn; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public String getTfn() { return tfn; }
    public void setTfn(String tfn) { this.tfn = tfn; }
    public String getAcn() { return acn; }
    public void setAcn(String acn) { this.acn = acn; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}