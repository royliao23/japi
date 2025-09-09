package com.app.controller;

import com.app.model.Contractor;
import com.app.repository.ContractorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/high/contractor")
public class ContractorController {

    private final ContractorRepository contractorRepository;

    public ContractorController(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    // Create contractor (POST /high/contractor)
    @PostMapping
    public ResponseEntity<Contractor> createContractor(@RequestBody Contractor contractor) {
        Contractor saved = contractorRepository.save(contractor);
        return ResponseEntity.status(201).body(saved);
    }

    // Get all contractors (GET /high/contractor)
    @GetMapping
    public ResponseEntity<List<Contractor>> getAllContractors() {
        return ResponseEntity.ok(contractorRepository.findAll());
    }

    // Get one contractor (GET /high/contractor/{id})
    @GetMapping("/{id}")
    public ResponseEntity<?> getContractor(@PathVariable Integer id) {
        Optional<Contractor> contractor = contractorRepository.findById(id);
        if (contractor.isEmpty()) {
            return ResponseEntity.status(404).body("{\"detail\":\"Contractor not found\"}");
        }
        return ResponseEntity.ok(contractor.get());
    }

    // Update contractor (PUT /high/contractor/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContractor(@PathVariable Integer id, @RequestBody Contractor update) {
        Optional<Contractor> contractorOpt = contractorRepository.findById(id);
        if (contractorOpt.isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorResponse("Contractor not found"));
        }

        Contractor contractor = contractorOpt.get();
        contractor.setContactPerson(update.getContactPerson());
        contractor.setCompanyName(update.getCompanyName());
        contractor.setPhoneNumber(update.getPhoneNumber());
        contractor.setEmail(update.getEmail());
        contractor.setBsb(update.getBsb());
        contractor.setAccountNo(update.getAccountNo());
        contractor.setAccountName(update.getAccountName());
        contractor.setAddress(update.getAddress());
        contractor.setAbn(update.getAbn());
        contractor.setGstRegistered(update.getGstRegistered());

        contractorRepository.save(contractor);
        return ResponseEntity.ok(new MessageResponse("Contractor updated successfully"));
    }

    // Delete contractor (DELETE /high/contractor/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContractor(@PathVariable Integer id) {
        Optional<Contractor> contractor = contractorRepository.findById(id);
        if (contractor.isEmpty()) {
            return ResponseEntity.status(404).body(new ErrorResponse("Contractor not found"));
        }

        contractorRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Contractor deleted successfully"));
    }

    // Small helper response classes
    public record MessageResponse(String message) {}
    public record ErrorResponse(String detail) {}
}


