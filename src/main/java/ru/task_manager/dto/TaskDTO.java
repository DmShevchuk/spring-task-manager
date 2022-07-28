package ru.task_manager.dto;

import ru.task_manager.entities.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.factories.TaskType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private TaskType type;
    private Long ownerId;
    private List<CommentDTO> commentEntityList;

    public static TaskDTO toDTO(TaskEntity entity) {
        TaskDTO task = new TaskDTO();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setDeadline(entity.getDeadline());
        task.setType(entity.getType());
        task.setOwnerId(entity.getUser().getId());
        task.setCommentEntityList(entity
                .getComments()
                .stream()
                .map(CommentDTO::toDTO)
                .collect(Collectors.toList()));
        return task;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + sdf.format(deadline) +
                ", type=" + type +
                ", ownerId=" + ownerId +
                '}';
    }
}
