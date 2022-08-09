package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.comment.CommentDTO;
import ru.task_manager.dto.comment.CommentSaveDTO;
import ru.task_manager.entities.CommentEntity;
import ru.task_manager.services.CommentService;
import ru.task_manager.utils.CustomCommentEntityMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CommentController {

    private final CommentService commentService;
    private final CustomCommentEntityMapper customModelMapper;
    private final ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создание нового комментария")
    public Long addNewComment(@Valid @RequestBody CommentSaveDTO commentSaveDTO) {
        CommentEntity commentEntity = customModelMapper.map(commentSaveDTO);
        Long taskId = commentSaveDTO.getTaskId();
        return commentService.create(commentEntity, taskId);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение списка всех комментариев")
    public Page<CommentDTO> getAllComments(@PageableDefault Pageable pageable) {
        Page<CommentEntity> comments = commentService.getAll(pageable);
        return comments.map(c -> modelMapper.map(c, CommentDTO.class));
    }


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение комментария по id")
    public CommentDTO getCommentById(@PathVariable Long id) {
        return CommentDTO.toDTO(commentService.getCommentById(id));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Обновление комментария по id")
    public CommentDTO updateTaskById(@PathVariable Long id,
                                     @Valid @RequestBody CommentSaveDTO commentSaveDTO) {
        CommentEntity commentEntity = customModelMapper.map(commentSaveDTO);
        commentEntity.setId(id);
        Long taskId = commentSaveDTO.getTaskId();
        return CommentDTO.toDTO(commentService.update(commentEntity, taskId));
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Удаление комментария по id")
    public String deleteCommentByID(@PathVariable Long id) {
        commentService.delete(id);
        return "Comment was deleted successfully!";
    }
}
