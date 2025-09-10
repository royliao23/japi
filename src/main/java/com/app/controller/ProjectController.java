package com.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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

import com.app.model.Project;
import com.app.repository.ProjectCodeNameDto;
import com.app.repository.ProjectCreationResponse;
import com.app.repository.ProjectRepository;

import jakarta.validation.Valid;

/**
 * REST Controller for managing projects.
 * This class handles all the HTTP requests and corresponds to the APIRouter in FastAPI.
 */
@RestController
@RequestMapping("/high/projects/")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a new project.
     * This method corresponds to the `create_project` endpoint in the FastAPI code.
     *
     * @param project The project data from the request body.
     * @return A ResponseEntity containing the created project's code and a success message.
     */
    @PostMapping
    public ResponseEntity<ProjectCreationResponse> createProject(@Valid @RequestBody Project project) {
        Project savedProject = projectRepository.save(project);
        ProjectCreationResponse response = new ProjectCreationResponse(
            savedProject.getCode(), 
            savedProject.getProjectName(), 
            "Project created successfully"
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all projects.
     * This method corresponds to the `read_projects` endpoint in the FastAPI code.
     *
     * @return A map with a "projects" key containing a list of all projects.
     */
    @GetMapping
    public Map<String, List<Project>> readAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return Collections.singletonMap("projects", projects);
    }

    /**
     * Retrieves a list of all projects with only their code and name.
     * This method corresponds to the `/high/projects/pjcodeandname` endpoint in the FastAPI code.
     *
     * @return A list of DTOs containing the project code and name.
     */
    @GetMapping("pjcodeandname/")
    public List<ProjectCodeNameDto> readProjectCodeAndName() {
        return projectRepository.findProjectCodeAndName();
    }

    /**
     * Retrieves a single project by its ID.
     * This method corresponds to the `read_project` endpoint in the FastAPI code.
     *
     * @param projectId The ID of the project to retrieve.
     * @return The found project.
     * @throws ResponseStatusException if the project is not found.
     */
    @GetMapping("{projectId}/")
    public Project readProjectById(@PathVariable Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    /**
     * Updates an existing project.
     * This method corresponds to the `update_project` endpoint in the FastAPI code.
     *
     * @param projectId The ID of the project to update.
     * @param updatedProject The updated project data from the request body.
     * @return A ResponseEntity with a success message.
     * @throws ResponseStatusException if the project is not found.
     */
    @PutMapping("{projectId}/")
    public ResponseEntity<Map<String, String>> updateProject(@PathVariable Long projectId, @Valid @RequestBody Project updatedProject) {
        // Find the project by ID or throw a 404
        Project projectToUpdate = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        // Update the project details
        projectToUpdate.setProjectName(updatedProject.getProjectName());
        projectToUpdate.setDescription(updatedProject.getDescription());
        projectToUpdate.setStatus(updatedProject.getStatus());
        projectToUpdate.setManager(updatedProject.getManager());

        projectRepository.save(projectToUpdate);

        return ResponseEntity.ok(Collections.singletonMap("message", "Project updated successfully"));
    }

    /**
     * Deletes a project by its ID.
     * This method corresponds to the `delete_category` endpoint in the FastAPI code.
     *
     * @param projectId The ID of the project to delete.
     * @return A ResponseEntity with a success message.
     * @throws ResponseStatusException if the project is not found.
     */
    @DeleteMapping("{projectId}/")
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        projectRepository.deleteById(projectId);
        return ResponseEntity.ok(Collections.singletonMap("message", "Project deleted successfully"));
    }
}
