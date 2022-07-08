package ru.task_manager.services;

import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.repositories.TaskRepo;
import ru.task_manager.repositories.UserRepo;

import java.util.List;

/**
 * Класс для работы с базой данных и объектами класса {@link TaskEntity}
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskEntity add(TaskEntity task, Long userId) throws UserNotFoundException {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        task.setUser(userEntity);
        taskRepo.save(task);
        return task;
    }

    public TaskEntity update(TaskEntity task, Long userId) throws TaskNotFoundException, UserNotFoundException {
        taskRepo.findById(task.getId())
                .orElseThrow(() -> new TaskNotFoundException(task.getId().toString()));
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        task.setUser(userEntity);
        return taskRepo.save(task);
    }

    public TaskEntity getById(Long id) throws TaskNotFoundException {
        return taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id.toString()));
    }

    public List<TaskEntity> getAll() {
        return taskRepo.findAll();
    }

    public void delete(Long id) throws TaskNotFoundException {
        taskRepo.deleteById(id);
    }

    public void deleteAll() {
        taskRepo.deleteAll();
    }
}
