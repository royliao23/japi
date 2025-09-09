package com.app.controller;

import com.app.model.JmProject;
import com.app.service.JmProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/high/projects")
public class JmProjectController {
    
    private final JmProjectService service;
    private static final Logger logger = LoggerFactory.getLogger(JmProjectController.class);
    public JmProjectController(JmProjectService service) {
        this.service = service;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JmProject createProject(@RequestBody JmProject project) {
        return service.createProject(project);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<JmProject> getAllProjects() {
        // Log SecurityContextHolder content
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("SecurityContextHolder content: {}", authentication);
        
        // Log detailed user info
        if (authentication != null) {
            logger.info("User: {}", authentication.getName());
            logger.info("Roles: {}", authentication.getAuthorities());
            logger.info("Authenticated: {}", authentication.isAuthenticated());
        }
        
        return service.getAllProjects();
    }
    
    @GetMapping("/status/{status}")
    public List<JmProject> getProjectsByStatus(@PathVariable String status) {
        return service.getProjectsByStatus(status);
    }
    // READ single
    @GetMapping("/{id}")
    public ResponseEntity<JmProject> getProject(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<JmProject> updateProject(@PathVariable Integer id,
                                                 @RequestBody JmProject project) {
        return ResponseEntity.ok(service.update(id, project));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}