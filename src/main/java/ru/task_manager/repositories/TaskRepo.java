package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task_manager.entities.TaskEntity;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> getTaskEntitiesByUserId(Long userId);
}
