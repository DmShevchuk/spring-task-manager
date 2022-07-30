package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.task_manager.entities.CommentEntity;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long>, JpaSpecificationExecutor<CommentEntity> {
}
