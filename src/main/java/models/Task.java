package models;

import entities.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tasks.TaskType;

import java.util.Date;

/**
 * Модель {@link TaskEntity} для представления данных пользователю
 */
@NoArgsConstructor
@Getter
@Setter
public class Task {
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private TaskType type;
    private Long ownerId;

    public static Task toModel(TaskEntity entity) {
        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setDeadline(entity.getDeadline());
        task.setType(entity.getType());
        task.setOwnerId(entity.getUser().getId());
        return task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", type=" + type +
                ", ownerId=" + ownerId +
                '}';
    }
}
