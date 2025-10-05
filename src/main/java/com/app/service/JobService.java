package com.app.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Job;
import com.app.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getJobsByCategory(List<Long> categoryCodes) {
        // Check if categoryCodes is empty
        if (categoryCodes == null || categoryCodes.isEmpty()) {
            return Collections.emptyList();
        }

        // Use the repository method (choose one of the three options)
        return jobRepository.findJobsByCategoryCodesNative(categoryCodes);
        
        // Alternative: return jobRepository.findJobsByCategoryCodes(categoryCodes);
        // Alternative: return jobRepository.findJobsByCategoryCodesNative(categoryCodes);
    }
}
