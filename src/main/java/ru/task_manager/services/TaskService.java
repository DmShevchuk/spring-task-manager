package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.repositories.ProjectRepo;
import ru.task_manager.repositories.TaskRepo;
import ru.task_manager.repositories.UserRepo;
import ru.task_manager.specification.CommonSpecificationFactory;

import java.util.List;

/**
 * Класс для работы с базой данных и объектами класса {@link TaskEntity}
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserService userService;
    private final ProjectService projectService;
    private final CommonSpecificationFactory specificationFactory;

    public Long create(TaskEntity taskEntity, Long userId, Long projectId) {
        UserEntity userEntity = userService.getUserById(userId);
        ProjectEntity projectEntity = projectService.getProjectById(projectId);
        taskEntity.setUser(userEntity);
        taskEntity.setProjectEntity(projectEntity);
        return taskRepo.save(taskEntity).getId();
    }


    public TaskEntity update(TaskEntity taskEntity, Long taskId, Long userId, Long projectId) {
        UserEntity userEntity = userService.getUserById(userId);
        ProjectEntity projectEntity = projectService.getProjectById(projectId);
        taskEntity.setUser(userEntity);
        taskEntity.setProjectEntity(projectEntity);
        taskEntity.setId(taskId);
        return taskRepo.save(taskEntity);
    }


    public TaskEntity getTaskById(Long id){
        return taskRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Task", id));
    }


    public Page<TaskEntity> getAll(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }


    public void delete(Long id) {
        taskRepo.deleteById(id);
    }


    public void deleteAll() {
        taskRepo.deleteAll();
    }

    public Page<TaskEntity> getTasksByUser(UserEntity userEntity, Pageable pageable) {
        return taskRepo.findAll(specificationFactory.getUserTasks(userEntity), pageable);
    }

    public Page<TaskEntity> getProjectTasks(ProjectEntity projectEntity, Pageable pageable) {
        return taskRepo.findAll(specificationFactory.getProjectTasks(projectEntity), pageable);
    }

    /**
     * Метод, осуществляющий создание задачи. Использовался до введения новых сущностей<br/>
     * Используйте {@link TaskService#create(TaskEntity, Long, Long)}
     * */
    @Deprecated
    public TaskEntity add(TaskEntity task, Long userId){
        UserEntity userEntity = userService.getUserById(userId);
        task.setUser(userEntity);
        taskRepo.save(task);
        return task;
    }


    /**
     * Метод, осуществляющий обновление задачи. Использовался до введения новых сущностей<br/>
     * Используйте {@link TaskService#update(TaskEntity, Long, Long, Long)}
     * */
    @Deprecated
    public TaskEntity update(TaskEntity task, Long userId) {
        if (!taskRepo.existsById(task.getId())) {throw new EntityNotFoundException("Task", task.getId());}
        UserEntity userEntity = userService.getUserById(userId);
        task.setUser(userEntity);
        return taskRepo.save(task);
    }
}
