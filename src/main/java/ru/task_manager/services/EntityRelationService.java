package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class EntityRelationService {

    @PersistenceContext
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final CommentService commentService;


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
