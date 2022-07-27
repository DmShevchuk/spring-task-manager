package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task_manager.entities.CommentEntity;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByTaskEntityId(Long taskId);
    CommentEntity findCommentByIdAndTaskEntityId(Long commentId, Long taskId);
}
