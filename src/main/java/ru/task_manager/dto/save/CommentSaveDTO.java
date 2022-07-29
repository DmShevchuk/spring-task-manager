package ru.task_manager.dto.save;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CommentSaveDTO {
    @NotBlank(message = "Text not specified!")
    private String text;
    @NotNull(message = "Task id can't be null!")
    private Long taskId;
}
