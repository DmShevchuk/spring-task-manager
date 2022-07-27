package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.exceptions.CommentNotFoundException;
import ru.task_manager.repositories.CommentRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;

    public void addNewComment(CommentEntity commentEntity){
        commentRepo.save(commentEntity);
    }

    public void updateComment(CommentEntity commentEntity){
        commentRepo.save(commentEntity);
    }

    public CommentEntity getCommentById(Long id){
        return commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException(id.toString()));
    }

    public List<CommentEntity> getAllComment(){
        return commentRepo.findAll();
    }

    public void deleteCommentById(Long id){
        commentRepo.deleteById(id);
    }
}
