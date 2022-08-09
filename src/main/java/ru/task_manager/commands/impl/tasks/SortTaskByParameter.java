package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.dto.task.TaskDTO;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, реализующий функционал сортировки задач по 3 параметрам: title, description, deadline, type
 * */
@RequiredArgsConstructor
public class SortTaskByParameter implements Command {
    private final List<TaskEntity> taskEntityList;
    private final String parameter;

    @Override
    public String execute() {
        switch (parameter){
            case "title" -> taskEntityList.sort(Comparator.comparing(TaskEntity::getTitle));
            case "description" -> taskEntityList.sort(Comparator.comparing(TaskEntity::getDescription));
            case "deadline" -> taskEntityList.sort(Comparator.comparing(TaskEntity::getDeadline));
            default -> taskEntityList.sort(Comparator.comparing(TaskEntity::getType));
        }
        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(TaskDTO.toDTO(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
