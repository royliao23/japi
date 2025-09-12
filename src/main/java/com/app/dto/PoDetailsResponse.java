package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.model.Jobby;
import com.app.model.Po;
import java.util.List;
import java.util.Map;

/**
 * DTO to format the response for the `/` endpoint.
 * This class extends the base PO model and adds the "invoice" and "job_name" fields.
 */
public class PoDetailsResponse extends Po {

    private List<Jobby> invoice;
    @JsonProperty("job_name")
    private Map<String, Object> jobName;

    public PoDetailsResponse(Po po, List<Jobby> invoice, Map<String, Object> jobName) {
        this.setCode(po.getCode());
        this.setCost(po.getCost());
        this.setDescription(po.getDescription());
        this.setJobId(po.getJobId());
        this.setById(po.getById());
        this.setProjectId(po.getProjectId());
        this.setRef(po.getRef());
        this.setDueAt(po.getDueAt());
        this.setContact(po.getContact());
        this.setNote(po.getNote());
        this.setCreateAt(po.getCreateAt());
        this.setUpdatedAt(po.getUpdatedAt());
        this.invoice = invoice;
        this.jobName = jobName;
    }

    // Getters
    public List<Jobby> getInvoice() { return invoice; }
    public Map<String, Object> getJobName() { return jobName; }

    // Setters
    public void setInvoice(List<Jobby> invoice) { this.invoice = invoice; }
    public void setJobName(Map<String, Object> jobName) { this.jobName = jobName; }
}

