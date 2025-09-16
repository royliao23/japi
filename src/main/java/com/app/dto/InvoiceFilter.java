package com.app.dto;

import java.util.List;


public class InvoiceFilter {
    private List<Integer> jobCodes;
    private Integer projectCode;

    public List<Integer> getJobCodes() {
        return jobCodes;
    }

    public void setJobCodes(List<Integer> jobCodes) {
        this.jobCodes = jobCodes;
    }

    public Integer getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(Integer projectCode) {
        this.projectCode = projectCode;
    }
}
