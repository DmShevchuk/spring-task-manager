package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.dto.TaskDTO;

import java.util.List;

/**
 * Класс выводящий список задач
 */
@RequiredArgsConstructor
public class ShowTasks implements Command {
    private final List<TaskEntity> taskEntityList;

    @Override
    public String execute() {
        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(TaskDTO.toDTO(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
