package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.CommentDTO;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.dto.save.TaskSaveDTO;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.services.CommentService;
import ru.task_manager.services.TaskService;
import ru.task_manager.utils.CustomTaskEntityMapper;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;
    private final CustomTaskEntityMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Добавление новой задачи")
    public Long addTask(@Valid @RequestBody TaskSaveDTO taskSaveDTO) {
        Long userId = taskSaveDTO.getUserId();
        Long projectId = taskSaveDTO.getProjectId();
        TaskEntity taskEntity = modelMapper.map(taskSaveDTO);
        return taskService.create(taskEntity, userId, projectId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех задач")
    public Page<TaskDTO> getAllTasks(@PageableDefault Pageable pageable) {
        return new PageImpl<>(
                taskService.getAll(pageable)
                        .stream()
                        .map(TaskDTO::toDTO)
                        .collect(toList()));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение задачи по id")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return TaskDTO.toDTO(taskService.getTaskById(id));
    }


    @GetMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех комментариев задачи")
    public Page<CommentDTO> getTaskComments(@PathVariable Long id,
                                      @PageableDefault Pageable pageable) {
        TaskEntity taskEntity = taskService.getTaskById(id);
        return new PageImpl<>(
                commentService.getCommentsByTask(taskEntity, pageable)
                        .stream()
                        .map(CommentDTO::toDTO)
                        .toList());
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Обновление задачи по id")
    public TaskDTO updateTaskById(@PathVariable Long id,
                                  @Valid @RequestBody TaskSaveDTO taskSaveDTO) {
        Long userId = taskSaveDTO.getUserId();
        Long projectId = taskSaveDTO.getProjectId();
        TaskEntity taskEntity = modelMapper.map(taskSaveDTO);
        return TaskDTO.toDTO(taskService.update(taskEntity, id, userId, projectId));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Удаление задачи по id")
    public String deleteTaskById(@PathVariable Long id) {
        taskService.delete(id);
        return "Task was deleted successfully!";
    }
}
