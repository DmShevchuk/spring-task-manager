package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.ProjectNotFoundException;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserNotFoundException;
import ru.task_manager.repositories.ProjectRepo;
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
    private final ProjectRepo projectRepo;


    public Long create(TaskEntity taskEntity, Long userId, Long projectId) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        ProjectEntity projectEntity = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId.toString()));
        taskEntity.setUser(userEntity);
        taskEntity.setProjectEntity(projectEntity);
        return taskRepo.save(taskEntity).getId();
    }


    public TaskEntity update(TaskEntity taskEntity, Long taskId, Long userId, Long projectId) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        ProjectEntity projectEntity = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId.toString()));
        taskEntity.setUser(userEntity);
        taskEntity.setProjectEntity(projectEntity);
        taskEntity.setId(taskId);
        return taskRepo.save(taskEntity);
    }


    public TaskEntity getTaskById(Long id) throws TaskNotFoundException {
        return taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id.toString()));
    }


    public List<TaskEntity> getAll() {
        return taskRepo.findAll();
    }


    public void delete(Long id) {
        taskRepo.deleteById(id);
    }


    public void deleteAll() {
        taskRepo.deleteAll();
    }


    /**
     * Метод, осуществляющий создание задачи. Использовался до введения новых сущностей<br/>
     * Используйте {@link TaskService#create(TaskEntity, Long, Long)}
     * */
    @Deprecated
    public TaskEntity add(TaskEntity task, Long userId) throws UserNotFoundException {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        task.setUser(userEntity);
        taskRepo.save(task);
        return task;
    }


    /**
     * Метод, осуществляющий обновление задачи. Использовался до введения новых сущностей<br/>
     * Используйте {@link TaskService#update(TaskEntity, Long, Long, Long)}
     * */
    @Deprecated
    public TaskEntity update(TaskEntity task, Long userId) throws TaskNotFoundException, UserNotFoundException {
        taskRepo.findById(task.getId())
                .orElseThrow(() -> new TaskNotFoundException(task.getId().toString()));
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        task.setUser(userEntity);
        return taskRepo.save(task);
    }
}
