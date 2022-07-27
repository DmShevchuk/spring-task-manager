package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.TaskEntity;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
}
