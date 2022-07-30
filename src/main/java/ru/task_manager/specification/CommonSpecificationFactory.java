package ru.task_manager.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.task_manager.entities.*;

@Component
public class CommonSpecificationFactory {
    public Specification<TaskEntity> getUserTasks(UserEntity user){
        return (root, query, cb) -> cb.equal(root.get(TaskEntity_.user), user);
    }

    public Specification<ProjectEntity> getUserProject(UserEntity user) {
        return (root, query, cb) -> cb.isMember(user, root.get(ProjectEntity_.usersEntity));
    }
}
