package models;

import entities.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tasks.TaskType;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Task {
    private Long id;
    private String title;
    private String description;
    private Date deadline;
    private TaskType type;

    public static Task toModel(TaskEntity entity){
        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDeadline(entity.getDeadline());
        task.setType(entity.getType());

        return task;
    }
}
