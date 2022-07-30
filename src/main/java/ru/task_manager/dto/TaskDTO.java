package ru.task_manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.factories.TaskType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private Long projectId;

    public static TaskDTO toDTO(TaskEntity entity) {
        TaskDTO task = new TaskDTO();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setDeadline(entity.getDeadline());
        task.setType(entity.getType());
        UserEntity user = entity.getUser();
        if(user != null){task.setOwnerId(user.getId());}
        ProjectEntity project = entity.getProjectEntity();
        if(project != null){task.setProjectId(project.getId());}
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
