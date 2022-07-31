package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.ProjectDTO;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.dto.UserDTO;
import ru.task_manager.dto.save.ProjectSaveDTO;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.services.ProjectService;
import ru.task_manager.services.TaskService;
import ru.task_manager.services.UserService;
import ru.task_manager.utils.CustomProjectEntityMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;
    private final CustomProjectEntityMapper modelMapper;


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
    public Page<ProjectDTO> getAllProjects(@PageableDefault Pageable pageable) {
        return new PageImpl<>(
                projectService.getAllProjects(pageable)
                        .stream()
                        .map(ProjectDTO::toDTO)
                        .toList());
    }


    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех задач проекта")
    public Page<TaskDTO> getProjectTasks(@PathVariable Long id,
                                         @PageableDefault Pageable pageable) {
        ProjectEntity projectEntity = projectService.getProjectById(id);
        return new PageImpl<>(
                taskService.getProjectTasks(projectEntity, pageable)
                        .stream()
                        .map(TaskDTO::toDTO)
                        .toList());
    }


    @GetMapping("/{id}/users")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех исполнителей проекта")
    public Page<UserDTO> getProjectUsers(@PathVariable Long id,
                                         @PageableDefault Pageable pageable) {
        ProjectEntity projectEntity = projectService.getProjectById(id);
        return new PageImpl<>(
                userService.getProjectUsers(projectEntity, pageable)
                        .stream()
                        .map(UserDTO::toDTO)
                        .toList());
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
        projectService.delete(id);
        return "Project was deleted successfully!";
    }
}
