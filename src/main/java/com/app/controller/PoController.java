package com.app.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.dto.InvoiceWithPaymentsResponse;
import com.app.dto.PoCreationResponse;
import com.app.dto.PoDetailsResponse;
import com.app.model.Jobby;
import com.app.model.Po;
import com.app.repository.JobRepository;
import com.app.repository.JobbyRepository;
import com.app.repository.PayRepository;
import com.app.repository.PoRepository;
import com.app.service.InvoiceService;

import jakarta.validation.Valid;

/**
 * REST Controller for managing purchase orders.
 * This class handles all the HTTP requests and corresponds to the APIRouter in FastAPI.
 */
@RestController
@RequestMapping("/high/po/")
public class PoController {

    private final PoRepository poRepository;
    private final JobbyRepository jobbyRepository;
    private final PayRepository payRepository;
    private final JobRepository jobRepository;
    private final InvoiceService invoiceService;

    @Autowired
    public PoController(PoRepository poRepository, JobbyRepository jobbyRepository, PayRepository payRepository, JobRepository jobRepository, InvoiceService invoiceService) {
        this.poRepository = poRepository;
        this.jobbyRepository = jobbyRepository;
        this.payRepository = payRepository;
        this.jobRepository = jobRepository;
        this.invoiceService = invoiceService;
    }

    /**
     * Creates a new purchase order.
     * This method corresponds to the `create_po` endpoint in the FastAPI code.
     *
     * @param po The PO data from the request body.
     * @return A ResponseEntity containing the created PO's details.
     */
    @PostMapping
    public ResponseEntity<PoCreationResponse> createPo(@Valid @RequestBody Po po) {
        Po savedPo = poRepository.save(po);
        PoCreationResponse response = new PoCreationResponse(
            savedPo.getCode(),
            savedPo.getCost(),
            savedPo.getDescription(),
            savedPo.getJobId(),
            savedPo.getById(),
            savedPo.getProjectId(),
            savedPo.getRef(),
            savedPo.getDueAt(),
            savedPo.getContact(),
            savedPo.getNote(),
            savedPo.getCreateAt(),
            savedPo.getUpdatedAt()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all purchase orders with related invoice and job details.
     * This method corresponds to the `read_pos` endpoint in the FastAPI code.
     * NOTE: This approach has an N+1 query problem, just like the original FastAPI code.
     *
     * @return A list of enriched POs.
     */
    @GetMapping
    public List<PoDetailsResponse> readPos() {
        List<Po> pos = poRepository.findAll();
        
        return pos.stream()
            .map(po -> {
                List<Jobby> invoices = jobbyRepository.findByPoId(po.getCode());
                Map<String, Object> jobName = jobRepository.findById(po.getJobId())
                    .map(job -> {
                        Map<String, Object> jobMap = new HashMap<>();
                        jobMap.put("code", job.getCode());
                        jobMap.put("name", job.getName());
                        return jobMap;
                    })
                    .orElse(Collections.emptyMap());
                return new PoDetailsResponse(po, invoices, jobName);
            })
            .collect(Collectors.toList());
    }

    /**
     * Retrieves a single PO by its ID.
     * This method corresponds to the `read_po` endpoint in the FastAPI code.
     *
     * @param poId The ID of the PO to retrieve.
     * @return A map with a "po" key containing the found PO.
     * @throws ResponseStatusException if the PO is not found.
     */
    @GetMapping("{poId}/")
    public Map<String, Po> readPoById(@PathVariable Long poId) {
        Po po = poRepository.findById(poId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PO not found"));
        return Collections.singletonMap("po", po);
    }

    /**
     * Updates an existing PO.
     * This method corresponds to the `update_po` endpoint in the FastAPI code.
     *
     * @param poId The ID of the PO to update.
     * @param updatedPo The updated PO data from the request body.
     * @return A ResponseEntity with a success message.
     * @throws ResponseStatusException if the PO is not found.
     */
    @PutMapping("{poId}/")
    public ResponseEntity<Map<String, String>> updatePo(@PathVariable Long poId, @Valid @RequestBody Po updatedPo) {
        Po poToUpdate = poRepository.findById(poId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PO not found"));

        poToUpdate.setCost(updatedPo.getCost());
        poToUpdate.setDescription(updatedPo.getDescription());
        poToUpdate.setJobId(updatedPo.getJobId());
        poToUpdate.setById(updatedPo.getById());
        poToUpdate.setProjectId(updatedPo.getProjectId());
        poToUpdate.setRef(updatedPo.getRef());
        poToUpdate.setDueAt(updatedPo.getDueAt());
        poToUpdate.setContact(updatedPo.getContact());
        poToUpdate.setNote(updatedPo.getNote());

        poRepository.save(poToUpdate);

        return ResponseEntity.ok(Collections.singletonMap("message", "po updated successfully"));
    }

    /**
     * Deletes a PO by its ID.
     * This method corresponds to the `delete_po` endpoint in the FastAPI code.
     *
     * @param poId The ID of the PO to delete.
     * @return A ResponseEntity with a success message.
     * @throws ResponseStatusException if the PO is not found.
     */
    @DeleteMapping("{poId}/")
    public ResponseEntity<Map<String, String>> deletePo(@PathVariable Long poId) {
        if (!poRepository.existsById(poId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PO not found");
        }
        poRepository.deleteById(poId);
        return ResponseEntity.ok(Collections.singletonMap("message", "po deleted successfully"));
    }

    /**
     * Retrieves a single invoice and its payment details.
     * This method corresponds to the `get_invoice_details` endpoint in the FastAPI code.
     *
     * @param invoiceId The ID of the invoice.
     * @return An enriched invoice object with payment and outstanding details.
     */
    // @GetMapping("inv/{invoiceId}/")
    // public InvoiceDetailsResponse getInvoiceDetails(@PathVariable Long invoiceId) {
    //     Jobby invoice = jobbyRepository.findById(invoiceId)
    //             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found"));

    //     List<Pay> payments = payRepository.findByInvoiceId(invoiceId);
    //     Double paidAmount = payments.stream().mapToDouble(Pay::getAmount).sum();
    //     Double outstandingAmount = invoice.getCost() - paidAmount;

    //     return new InvoiceDetailsResponse(invoice, payments, paidAmount, outstandingAmount);
    // }
    @GetMapping("inv/{invoiceId}/")
    public ResponseEntity<?> getInvoiceWithPayments(@PathVariable Long invoiceId) {
        try {
            InvoiceWithPaymentsResponse invoiceWithPayments = invoiceService.getInvoiceWithPayments(invoiceId);
            return ResponseEntity.ok(invoiceWithPayments);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invoice not found");
        }
    }
}
