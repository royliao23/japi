package com.app.dto;

import java.util.List;

import com.app.model.Jobby;
import com.app.model.Pay;

/**
 * DTO to format the response for the `/inv/{invoice_id}` endpoint.
 * Combines Jobby (invoice), Pay, and calculated fields.
 */
public class InvoiceDetailsResponse extends Jobby {

    private List<Pay> pay;
    private Double paid;
    private Double outstanding;

    public InvoiceDetailsResponse() {}

    public InvoiceDetailsResponse(Jobby jobby, List<Pay> pay, Double paid, Double outstanding) {
        this.setCode(jobby.getCode());
        this.setPoId(jobby.getPoId());
        this.setRef(jobby.getRef());
        this.setCost(jobby.getCost());
        this.setJobId(jobby.getJobId());
        this.setById(jobby.getById());
        this.setProjectId(jobby.getProjectId());
        
        this.pay = pay;
        this.paid = paid;
        this.outstanding = outstanding;
    }

    // Getters
    public List<Pay> getPay() { return pay; }
    public Double getPaid() { return paid; }
    public Double getOutstanding() { return outstanding; }

    // Setters
    public void setPay(List<Pay> pay) { this.pay = pay; }
    public void setPaid(Double paid) { this.paid = paid; }
    public void setOutstanding(Double outstanding) { this.outstanding = outstanding; }
}

