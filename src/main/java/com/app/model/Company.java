package com.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String companyName;
    private String address;
    private String abn;
    private String director;
    private String tfn;
    private String acn;
    private String phone;
    private String email;

    // Manual builder method
    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }

    public static class CompanyBuilder {
        private String companyName;
        private String address;
        private String abn;
        private String director;
        private String tfn;
        private String acn;
        private String phone;
        private String email;

        public CompanyBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }
        
        public CompanyBuilder address(String address) {
            this.address = address;
            return this;
        }
        
        public CompanyBuilder abn(String abn) {
            this.abn = abn;
            return this;
        }
        
        public CompanyBuilder director(String director) {
            this.director = director;
            return this;
        }
        
        public CompanyBuilder tfn(String tfn) {
            this.tfn = tfn;
            return this;
        }
        
        public CompanyBuilder acn(String acn) {
            this.acn = acn;
            return this;
        }
        
        public CompanyBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public CompanyBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public Company build() {
            Company company = new Company();
            company.setCompanyName(companyName);
            company.setAddress(address);
            company.setAbn(abn);
            company.setDirector(director);
            company.setTfn(tfn);
            company.setAcn(acn);
            company.setPhone(phone);
            company.setEmail(email);
            return company;
        }
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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