package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.CommentNotFoundException;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.repositories.CommentRepo;
import ru.task_manager.repositories.TaskRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final TaskRepo taskRepo;

    public void addNewComment(CommentEntity commentEntity, Long taskId) {
        TaskEntity taskEntity = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId.toString()));
        commentEntity.setTaskEntity(taskEntity);
        commentRepo.save(commentEntity);
    }

    public void updateComment(Long taskId, Long commentId, CommentEntity commentEntity) {
        if (!taskRepo.existsById(taskId)) throw new TaskNotFoundException(taskId.toString());

        commentRepo.findById(commentId).map(
                comment -> {
                    comment.setText(commentEntity.getText());
                    return commentRepo.save(comment);
                }
        ).orElseThrow(() -> new CommentNotFoundException(commentId.toString(), taskId.toString()));
    }

    public CommentEntity getCommentById(Long commentId, Long taskId) {
        return commentRepo.findCommentByIdAndTaskEntityId(commentId, taskId);
    }

    public List<CommentEntity> getAllComment(Long taskId) {
        return commentRepo.findAllByTaskEntityId(taskId);
    }

    public void deleteCommentById(Long taskId, Long commentId) {
        if (!taskRepo.existsById(taskId)) throw new TaskNotFoundException(taskId.toString());

        commentRepo.deleteById(commentId);
    }
}
