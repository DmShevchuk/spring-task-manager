package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects/")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<String> addNewProject(@RequestBody ProjectEntity projectEntity)
    {
        projectService.createProject(projectEntity);
        return new ResponseEntity<>("Project was created successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectEntity>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProjectById(@PathVariable Long id, @RequestBody ProjectEntity projectEntity){
        projectEntity.setId(id);
        projectService.updateProject(projectEntity);

        return new ResponseEntity<>("Project was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectByID(@PathVariable Long id){
        projectService.deleteById(id);

        return new ResponseEntity<>("Project was deleted successfully!", HttpStatus.OK);
    }

}
