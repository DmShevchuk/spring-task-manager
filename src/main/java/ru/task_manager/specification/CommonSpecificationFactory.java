package ru.task_manager.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.task_manager.entities.*;

@Component
public class CommonSpecificationFactory {

    /**
     * Поиск задач, принадлежащих конкретному пользователю
     * */
    public Specification<TaskEntity> getUserTasks(UserEntity user){
        return (root, query, cb) -> cb.equal(root.get(TaskEntity_.user), user);
    }


    /**
     * Поиск проектов, принадлежащих конкретному пользователю
     * */
    public Specification<ProjectEntity> getUserProject(UserEntity user) {
        return (root, query, cb) -> cb.isMember(user, root.get(ProjectEntity_.usersEntity));
    }


    /**
     * Поиск комментариев, принадлежащих конкретной задаче
     * */
    public Specification<CommentEntity> getTaskComments(TaskEntity taskEntity) {
        return (root, query, cb) -> cb.equal(root.get(CommentEntity_.taskEntity), taskEntity);
    }


    /**
     * Поиск задач, принадлежащих конкретному проекту
     * */
    public Specification<TaskEntity> getProjectTasks(ProjectEntity projectEntity){
        return (root, query, cb) -> cb.equal(root.get(TaskEntity_.projectEntity), projectEntity);

    }

    /**
     * Поиск пользователей, принадлежащих конкретному проекту
     * */
    public Specification<UserEntity> getProjectUsers(ProjectEntity projectEntity) {
        return (root, query, cb) -> cb.isMember(projectEntity, root.get(UserEntity_.projects));
    }
}
