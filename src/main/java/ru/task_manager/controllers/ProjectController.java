package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.ProjectDTO;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects/")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<String> addProject(@RequestBody ProjectEntity projectEntity) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllTasks() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TaskDTO>> getProjectById(@PathVariable Long id) {
        return null;
    }

    @PutMapping
    public ResponseEntity<String> updateProjectById( @RequestBody ProjectEntity projectEntity) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Long id) {
        return null;
    }
}
