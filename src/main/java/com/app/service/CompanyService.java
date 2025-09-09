package com.app.service;

import com.app.model.Company;
import com.app.dto.CompanyCreate;
import com.app.dto.CompanyUpdate;
import com.app.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company create(CompanyCreate dto) {
        Company company = Company.builder()
                .companyName(dto.getCompanyName())
                .address(dto.getAddress())
                .abn(dto.getAbn())
                .director(dto.getDirector())
                .tfn(dto.getTfn())
                .acn(dto.getAcn())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
        return repository.save(company);
    }

    public Company getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public List<Company> getAll(int skip, int limit) {
        // Using JPA paging
        return repository.findAll().stream()
                .skip(skip)
                .limit(limit)
                .toList();
    }

    public Company update(Long id, CompanyUpdate dto) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (dto.getCompanyName() != null) company.setCompanyName(dto.getCompanyName());
        if (dto.getAddress() != null) company.setAddress(dto.getAddress());
        if (dto.getAbn() != null) company.setAbn(dto.getAbn());
        if (dto.getDirector() != null) company.setDirector(dto.getDirector());
        if (dto.getTfn() != null) company.setTfn(dto.getTfn());
        if (dto.getAcn() != null) company.setAcn(dto.getAcn());
        if (dto.getPhone() != null) company.setPhone(dto.getPhone());
        if (dto.getEmail() != null) company.setEmail(dto.getEmail());

        return repository.save(company);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Company not found");
        }
        repository.deleteById(id);
    }
}
