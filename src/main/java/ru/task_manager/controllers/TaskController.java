package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.CommentDTO;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.dto.save.TaskSaveDTO;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.services.CommentService;
import ru.task_manager.services.TaskService;
import ru.task_manager.utils.CustomTaskEntityMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;
    private final CustomTaskEntityMapper customModelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Добавление новой задачи")
    public Long addTask(@Valid @RequestBody TaskSaveDTO taskSaveDTO) {
        Long userId = taskSaveDTO.getUserId();
        Long projectId = taskSaveDTO.getProjectId();
        TaskEntity taskEntity = customModelMapper.map(taskSaveDTO);
        return taskService.create(taskEntity, userId, projectId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех задач")
    public Page<TaskDTO> getAllTasks(@PageableDefault Pageable pageable) {
        Page<TaskEntity> tasks = taskService.getAll(pageable);
        return tasks.map(t -> modelMapper.map(t, TaskDTO.class));
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
        Page<CommentEntity> comments = commentService.getCommentsByTask(taskEntity, pageable);
        return comments.map(c -> modelMapper.map(c, CommentDTO.class));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Обновление задачи по id")
    public TaskDTO updateTaskById(@PathVariable Long id,
                                  @Valid @RequestBody TaskSaveDTO taskSaveDTO) {
        Long userId = taskSaveDTO.getUserId();
        Long projectId = taskSaveDTO.getProjectId();
        TaskEntity taskEntity = customModelMapper.map(taskSaveDTO);
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
