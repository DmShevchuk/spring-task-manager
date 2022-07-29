package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.ProjectDTO;
import ru.task_manager.dto.save.ProjectSaveDTO;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.services.ProjectService;
import ru.task_manager.utils.CustomProjectEntityMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final CustomProjectEntityMapper modelMapper;

    @PostMapping
    public ResponseEntity<Long> addProject(@Valid @RequestBody ProjectSaveDTO projectSaveDTO) {
        List<Long> usersIdList = projectSaveDTO.getUsersIdList();
        ProjectEntity projectEntity = modelMapper.map(projectSaveDTO);
        Long createdProjectId = projectService.createProject(projectEntity, usersIdList);
        return new ResponseEntity<>(createdProjectId, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projectDTOS = projectService.getAllProjects()
                .stream()
                .map(ProjectDTO::toDTO)
                .toList();
        return new ResponseEntity<>(projectDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        ProjectEntity projectEntity = projectService.getProjectById(id);
        return new ResponseEntity<>(
                ProjectDTO.toDTO(projectEntity),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProjectById(@PathVariable Long id,
                                                        @Valid @RequestBody ProjectSaveDTO projectSaveDTO) {
        List<Long> usersIdList = projectSaveDTO.getUsersIdList();
        ProjectEntity projectEntity = modelMapper.map(projectSaveDTO);
        projectEntity.setId(id);
        ProjectDTO projectDTO = ProjectDTO.toDTO(projectService.updateProject(projectEntity, usersIdList));
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Long id) {
        projectService.delete(id);
        return new ResponseEntity<>("Project was deleted successfully!", HttpStatus.OK);
    }
}
