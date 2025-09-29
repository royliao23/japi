package com.app.dto;

import com.app.model.Jobby;
import com.app.model.Pay;

public class EnhancedPayResponse {
    private Pay pay;
    private Jobby jobby;
    
    
    public EnhancedPayResponse(Pay pay, Jobby jobby) {
        this.pay = pay;
        this.jobby = jobby;
    }

   
    
    // Getters and setters
    public Pay getPay() { return pay; }
    public void setPay(Pay pay) { this.pay = pay; }
    
    public Jobby getJobby() { return jobby; }
    public void setJobby(Jobby jobby) { this.jobby = jobby; }
}