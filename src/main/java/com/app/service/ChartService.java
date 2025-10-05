package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CategoryDataResponse;
import com.app.dto.JobDataResponse;
import com.app.dto.PayeeDataResponse;
import com.app.dto.ProjectCodeResponse;
import com.app.dto.ProjectDataResponse;
import com.app.repository.ChartRepository;

@Service
public class ChartService {

    @Autowired
    private ChartRepository chartRepository;

    public List<ProjectDataResponse> getProjectData() {
        return chartRepository.getProjectData();
    }

    public List<CategoryDataResponse> getJobCategoryData(Long projectId) {
        return chartRepository.getJobCategoryData(projectId);
    }

    public List<JobDataResponse> getJobData(Long projectId) {
        return chartRepository.getJobData(projectId);
    }

    public List<PayeeDataResponse> getPayeeData() {
        return chartRepository.getPayeeData();
    }

    public List<ProjectCodeResponse> getProjectCodes() {
        return chartRepository.getProjectCodes();
    }
}
