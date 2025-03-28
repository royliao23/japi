package com.example.controller;

import com.example.model.JmProject;
import com.example.service.JmProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class JmProjectController {
    
    private final JmProjectService service;
    
    public JmProjectController(JmProjectService service) {
        this.service = service;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JmProject createProject(@RequestBody JmProject project) {
        return service.createProject(project);
    }
    
    @GetMapping
    public List<JmProject> getAllProjects() {
        return service.getAllProjects();
    }
    
    @GetMapping("/status/{status}")
    public List<JmProject> getProjectsByStatus(@PathVariable String status) {
        return service.getProjectsByStatus(status);
    }
}