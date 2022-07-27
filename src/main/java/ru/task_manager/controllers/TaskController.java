package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks/")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody TaskEntity taskEntity){
        taskService.addNewTask(taskEntity);
        return new ResponseEntity<>("Task was added successfully!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        return new ResponseEntity<>(TaskDTO.toDTO(taskService.getTaskById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable Long id, @RequestBody TaskEntity taskEntity){
        taskEntity.setId(id);
        taskService.update(taskEntity);
        return new ResponseEntity<>("Task was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity<>("Task was deleted successfully!", HttpStatus.OK);
    }

}
