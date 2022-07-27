package ru.task_manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.entities.CommentEntity;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String text;

    public static CommentDTO toDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(commentEntity.getText());
        return commentDTO;
    }
}
