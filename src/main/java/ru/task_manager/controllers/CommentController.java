package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.CommentDTO;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.services.CommentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<String> addNewComment(@PathVariable Long taskId,
                                                @RequestBody CommentEntity commentEntity){
        commentService.addNewComment(commentEntity, taskId);
        return new ResponseEntity<>("Comment was added successfully!", HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable Long taskId){
        List<CommentEntity> commentEntityList = commentService.getAllComment(taskId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity commentEntity: commentEntityList){
            commentDTOList.add(CommentDTO.toDTO(commentEntity));
        }
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long taskId,
                                                        @PathVariable Long commentId){
        return new ResponseEntity<>(CommentDTO.toDTO(commentService.getCommentById(commentId, taskId)), HttpStatus.OK);
    }

    @PutMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<String> updateTaskById(@PathVariable Long taskId,
                                                 @PathVariable Long commentId,
                                                 @RequestBody CommentEntity commentEntity){
        commentService.updateComment(taskId, commentId, commentEntity);

        return new ResponseEntity<>("Comment was updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{taskId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByID(@PathVariable Long taskId,
                                                    @PathVariable Long commentId){
        commentService.deleteCommentById(taskId, commentId);
        return new ResponseEntity<>("Comment was deleted successfully!", HttpStatus.OK);
    }
}
