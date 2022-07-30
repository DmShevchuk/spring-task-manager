package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.ProjectDTO;
import ru.task_manager.dto.save.ProjectSaveDTO;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.services.EntityRelationService;
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
    private final EntityRelationService entityRelationService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создание нового проекта")
    public Long addProject(@Valid @RequestBody ProjectSaveDTO projectSaveDTO) {
        List<Long> usersIdList = projectSaveDTO.getUsersIdList();
        ProjectEntity projectEntity = modelMapper.map(projectSaveDTO);
        return projectService.create(projectEntity, usersIdList);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение списка всех проектов")
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects()
                .stream()
                .map(ProjectDTO::toDTO)
                .toList();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение проекта по id")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        ProjectEntity projectEntity = projectService.getProjectById(id);
        return ProjectDTO.toDTO(projectEntity);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Обновление проекта по id")
    public ProjectDTO updateProjectById(@PathVariable Long id,
                                                        @Valid @RequestBody ProjectSaveDTO projectSaveDTO) {
        List<Long> usersIdList = projectSaveDTO.getUsersIdList();
        ProjectEntity projectEntity = modelMapper.map(projectSaveDTO);
        return ProjectDTO.toDTO(projectService.update(projectEntity, id, usersIdList));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Удаление проекта по id; проект также удаляется у пользователей и задач")
    public String deleteProjectById(@PathVariable Long id) {
        entityRelationService.removeProjectFromUsers(id);
        entityRelationService.removeProjectFromTasks(id);
        projectService.delete(id);
        return "Project was deleted successfully!";
    }
}
