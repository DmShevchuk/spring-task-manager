package ru.task_manager.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.entities.CommentEntity;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String text;
    private Long taskId;

    public static CommentDTO toDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setText(commentEntity.getText());
        commentDTO.setTaskId(commentEntity.getId());
        return commentDTO;
    }
}
