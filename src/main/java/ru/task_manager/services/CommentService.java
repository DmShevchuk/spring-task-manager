package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.repositories.CommentRepo;
import ru.task_manager.specification.CommonSpecificationFactory;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final TaskService taskService;
    private final CommonSpecificationFactory specificationFactory;


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


    public Page<CommentEntity> getAll(Pageable pageable) {
        return commentRepo.findAll(pageable);
    }


    public void delete(Long id){
        commentRepo.deleteById(id);
    }


    public Page<CommentEntity> getCommentsByTask(TaskEntity taskEntity, Pageable pageable) {
        return commentRepo.findAll(specificationFactory.getTaskComments(taskEntity), pageable);
    }
}
