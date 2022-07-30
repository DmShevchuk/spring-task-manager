package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.dto.save.TaskSaveDTO;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.services.TaskService;
import ru.task_manager.utils.CustomTaskEntityMapper;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final CustomTaskEntityMapper modelMapper;


    @PostMapping
    @ApiOperation("Добавление новой задачи")
    public ResponseEntity<Long> addTask(@Valid @RequestBody TaskSaveDTO taskSaveDTO) {
        Long userId = taskSaveDTO.getUserId();
        Long projectId = taskSaveDTO.getProjectId();
        TaskEntity taskEntity = modelMapper.map(taskSaveDTO);
        Long createdTaskId = taskService.create(taskEntity, userId, projectId);
        return new ResponseEntity<>(createdTaskId, HttpStatus.CREATED);
    }


    @GetMapping
    @ApiOperation("Получение всех задач")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskDTOS = taskService.getAll()
                .stream()
                .map(TaskDTO::toDTO)
                .collect(toList());
        return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение всех задач по id")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO taskDTO = TaskDTO.toDTO(taskService.getTaskById(id));
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @ApiOperation("Обновление задачи по id")
    public ResponseEntity<TaskDTO> updateTaskById(@PathVariable Long id,
                                                  @Valid @RequestBody TaskSaveDTO taskSaveDTO) {
        Long userId = taskSaveDTO.getUserId();
        Long projectId = taskSaveDTO.getProjectId();
        TaskEntity taskEntity = modelMapper.map(taskSaveDTO);
        TaskDTO taskDTO = TaskDTO.toDTO(taskService.update(taskEntity, id, userId, projectId));
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Удаление задачи по id")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        taskService.delete(id);
        return new ResponseEntity<>("Task was deleted successfully!", HttpStatus.OK);
    }
}
