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
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody TaskEntity taskEntity) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TaskDTO>> getTaskById(@PathVariable Long id) {
        return null;
    }

    @PutMapping
    public ResponseEntity<String> updateTaskById( @RequestBody TaskEntity taskEntity) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        return null;
    }

}
