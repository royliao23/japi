package com.app.controller;

import com.app.dto.CompanyCreate;
import com.app.dto.CompanyUpdate;
import com.app.model.Company;
import com.app.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/high/company/")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyCreate dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    // READ single
    @GetMapping("{id}/")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // READ list
    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(
            @RequestParam(defaultValue = "0") int skip,
            @RequestParam(defaultValue = "100") int limit
    ) {
        return ResponseEntity.ok(service.getAll(skip, limit));
    }

    // UPDATE
    @PutMapping("{id}/")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id,
                                                 @RequestBody CompanyUpdate dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // DELETE
    @DeleteMapping("{id}/")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
