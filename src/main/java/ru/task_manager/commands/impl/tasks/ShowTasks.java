package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;

import java.util.List;

/**
 * Класс выводящий список задач
 */
@Component
@RequiredArgsConstructor
public class ShowTasks extends Command {
    private final TaskService taskService;

    @Override
    public String execute(String[] args) {
        isArgQuantityCorrect();
        List<TaskEntity> taskEntityList = taskService.getAll();
        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(TaskDTO.toDTO(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }

    @Override
    public String getName() {
        return "show_tasks";
    }

    @Override
    public String getInfo() {
        return "show all tasks in beauty table view";
    }

    @Override
    public int getArgsQuantity() {
        return 0;
    }
}
