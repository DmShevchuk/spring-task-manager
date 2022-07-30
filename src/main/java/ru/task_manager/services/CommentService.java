package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.repositories.CommentRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final TaskService taskService;


    public Long create(CommentEntity commentEntity, Long taskId) {
        TaskEntity taskEntity = taskService.getTaskById(taskId);
        commentEntity.setTaskEntity(taskEntity);
        System.out.println(commentEntity.getId());
        return commentRepo.save(commentEntity).getId();
    }


    public CommentEntity update(CommentEntity commentEntity, Long taskId) {
        TaskEntity taskEntity = taskService.getTaskById(taskId);
        commentEntity.setTaskEntity(taskEntity);
        return commentRepo.save(commentEntity);
    }


    public CommentEntity getCommentById(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment", id));
    }


    public List<CommentEntity> getAll() {
        return commentRepo.findAll();
    }


    public void delete(Long id){
        commentRepo.deleteById(id);
    }
}
