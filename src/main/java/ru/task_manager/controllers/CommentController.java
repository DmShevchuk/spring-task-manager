package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> addNewComment(@RequestBody CommentEntity commentEntity){
        commentService.addNewComment(commentEntity);
        return new ResponseEntity<>("Comment was added successfully!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentEntity>> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComment(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEntity> getCommentById(@PathVariable Long id){
        return new ResponseEntity<>(commentService.getCommentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTaskById(@PathVariable Long id, @RequestBody CommentEntity commentEntity){
        commentEntity.setId(id);
        commentService.updateComment(commentEntity);

        return new ResponseEntity<>("Comment was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentByID(@PathVariable Long id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>("Comment was deleted successfully!", HttpStatus.OK);
    }
}
