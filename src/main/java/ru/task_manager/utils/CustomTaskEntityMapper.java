package ru.task_manager.utils;

import org.springframework.stereotype.Component;
import ru.task_manager.dto.task.TaskSaveDTO;
import ru.task_manager.entities.TaskEntity;

@Component
public class CustomTaskEntityMapper {
    public TaskEntity map(TaskSaveDTO taskSaveDTO){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskSaveDTO.getTitle());
        taskEntity.setDescription(taskSaveDTO.getDescription());
        taskEntity.setDeadline(taskSaveDTO.getDeadline());
        taskEntity.setType(taskSaveDTO.getType());
        return taskEntity;
    }
}
