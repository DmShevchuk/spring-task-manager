package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, реализующий функционал сортировки задач по 3 параметрам: title, description, deadline, type
 * */
@Component
@RequiredArgsConstructor
public class SortTaskByParameter extends Command {
    private final TaskService taskService;
    private final int SORTING_PARAMETER_INDEX = 0;

    @Override
    public String execute(String[] args) {
        isArgQuantityCorrect();
        List<TaskEntity> taskEntityList = taskService.getAll();
        String parameter = args[SORTING_PARAMETER_INDEX];
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

    @Override
    public String getName() {
        return "sort_by_parameter";
    }

    @Override
    public String getInfo() {
        return "sort all tasks by parameter";
    }

    @Override
    public int getArgsQuantity() {
        return 1;
    }
}
