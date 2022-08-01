package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.task_manager.entities.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskEntity> {
}
