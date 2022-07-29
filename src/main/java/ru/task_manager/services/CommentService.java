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

    public Long addNewComment(CommentEntity commentEntity, Long taskId) {
        TaskEntity taskEntity = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId.toString()));
        commentEntity.setTaskEntity(taskEntity);
        return commentRepo.save(commentEntity).getId();
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

    public CommentEntity updateComment(CommentEntity commentEntity, Long taskId) {
        TaskEntity taskEntity = taskRepo.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId.toString()));
        commentEntity.setTaskEntity(taskEntity);
        return commentRepo.save(commentEntity);
    }

    public CommentEntity getCommentById(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException(id.toString(), "12"));
    }

    public List<CommentEntity> getAllComment(Long taskId) {
        return commentRepo.findAllByTaskEntityId(taskId);
    }

    public List<CommentEntity> getAll() {
        return commentRepo.findAll();
    }

    public void delete(Long id){
        commentRepo.deleteById(id);
    }

    public void deleteCommentById(Long taskId, Long commentId) {
        if (!taskRepo.existsById(taskId)) throw new TaskNotFoundException(taskId.toString());

        commentRepo.deleteById(commentId);
    }
}
