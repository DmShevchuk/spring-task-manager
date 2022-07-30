package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.CommentDTO;
import ru.task_manager.dto.save.CommentSaveDTO;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.services.CommentService;
import ru.task_manager.utils.CustomCommentEntityMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CustomCommentEntityMapper modelMapper;


    @PostMapping
    @ApiOperation("Создание нового комментария")
    public ResponseEntity<Long> addNewComment(@Valid @RequestBody CommentSaveDTO commentSaveDTO){
        CommentEntity commentEntity = modelMapper.map(commentSaveDTO);
        Long taskId = commentSaveDTO.getTaskId();
        Long addedCommentId = commentService.create(commentEntity, taskId);
        return new ResponseEntity<>(addedCommentId, HttpStatus.OK);
    }


    @GetMapping
    @ApiOperation("Получение списка всех комментариев")
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        List<CommentDTO> commentDTOS = commentService.getAll()
                .stream()
                .map(CommentDTO::toDTO).toList();
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @ApiOperation("Получение комментария по id")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id){
        CommentDTO commentDTO = CommentDTO.toDTO(commentService.getCommentById(id));
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }


    @PutMapping("{id}")
    @ApiOperation("Обновление комментария по id")
    public ResponseEntity<CommentDTO> updateTaskById(@PathVariable Long id,
                                                 @Valid @RequestBody CommentSaveDTO commentSaveDTO){
        CommentEntity commentEntity = modelMapper.map(commentSaveDTO);
        commentEntity.setId(id);
        Long taskId = commentSaveDTO.getTaskId();
        CommentDTO commentDTO = CommentDTO.toDTO(commentService.update(commentEntity, taskId));

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @ApiOperation("Удаление комментария по id")
    public ResponseEntity<String> deleteCommentByID(@PathVariable Long id){
        commentService.delete(id);
        return new ResponseEntity<>("Comment was deleted successfully!", HttpStatus.OK);
    }
}
