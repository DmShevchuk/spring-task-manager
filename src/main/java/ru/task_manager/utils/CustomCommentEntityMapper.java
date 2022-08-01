package ru.task_manager.utils;

import org.springframework.stereotype.Component;
import ru.task_manager.dto.save.CommentSaveDTO;
import ru.task_manager.entities.CommentEntity;

@Component
public class CustomCommentEntityMapper {
    public CommentEntity map(CommentSaveDTO commentSaveDTO){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setText(commentSaveDTO.getText());
        return commentEntity;
    }
}
