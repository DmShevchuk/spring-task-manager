package ru.task_manager.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.factories.TaskType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TaskSaveDTO {
    @NotBlank(message = "Title not specified!")
    private String title;
    @NotBlank(message = "Description not specified!")
    private String description;
    @NotNull(message = "Deadline can't be null!")
    private Date deadline;
    @NotNull(message = "Type of task not specified!")
    private TaskType type;
    @NotNull(message = "Users id can't be null!")
    private Long userId;
    @NotNull(message = "Id of project can't be null!")
    private Long projectId;
}
