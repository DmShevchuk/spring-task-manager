package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.task_manager.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    TaskEntity getTaskEntityByIdAndUserId(Long taskId, Long userId);
    List<TaskEntity> getTaskEntitiesByUserId(Long userId);
    List<TaskEntity> getTaskEntitiesByProjectEntityId(Long projectId);
}
