package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityRelationService {
    @PersistenceContext
    private EntityManager entityManager;
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;

    @Transactional
    public void removeUserFromProjects(Long userId){
        entityManager.createNativeQuery(
                "delete from users_projects " +
                        "where id_of_user = :userId"
        ).setParameter("userId", userId).executeUpdate();
    }

    @Transactional
    public void removeUserFromTasks(Long userId){
        entityManager.createNativeQuery(
                "update tasks " +
                        "set id_of_user = null " +
                        "where id_of_user = :userId"
        ).setParameter("userId", userId).executeUpdate();
    }

    @Transactional
    public void removeProjectFromUsers(Long projectId){
        entityManager.createNativeQuery(
                "delete from users_projects " +
                        "where id_of_project = :projectId"
        ).setParameter("projectId", projectId).executeUpdate();
    }

    @Transactional
    public void removeProjectFromTasks(Long projectId){
        entityManager.createNativeQuery(
                "update tasks " +
                        "set id_of_project = null " +
                        "where id_of_project = :projectId"
        ).setParameter("projectId", projectId).executeUpdate();
    }


    public List<TaskEntity> getUserTasks(UserEntity userEntity) {
        return taskService.getTasksByUser(userEntity);
    }


    public List<ProjectEntity> getUserProjects(UserEntity userEntity) {
        return projectService.getUserProjects(userEntity);
    }
}
