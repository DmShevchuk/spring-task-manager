package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityRelationService {

    @PersistenceContext
    private EntityManager entityManager;
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final CommentService commentService;


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


    public Page<TaskEntity> getUserTasks(UserEntity userEntity, Pageable pageable) {
        return taskService.getTasksByUser(userEntity, pageable);
    }

    public Page<CommentEntity> getTaskComments(TaskEntity taskEntity, Pageable pageable) {
        return commentService.getCommentByTask(taskEntity, pageable);
    }

    public Page<ProjectEntity> getUserProjects(UserEntity userEntity, Pageable pageable) {
        return projectService.getUserProjects(userEntity, pageable);
    }

    public Page<TaskEntity> getProjectTasks(ProjectEntity projectEntity, Pageable pageable) {
        return taskService.getProjectTasks(projectEntity, pageable);
    }

    public Page<UserEntity> getProjectUsers(ProjectEntity projectEntity, Pageable pageable) {
        return userService.getProjectUsers(projectEntity, pageable);
    }
}
