package com.app.dto;

import java.util.List;


public class InvoiceFilter {
    private List<Long> jobCodes;
    private Long projectCode;

    public List<Long> getJobCodes() {
        return jobCodes;
    }

    public void setJobCodes(List<Long> jobCodes) {
        this.jobCodes = jobCodes;
    }

    public Long getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(Long projectCode) {
        this.projectCode = projectCode;
    }
}
