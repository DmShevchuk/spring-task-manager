package ru.task_manager.commands.impl.tasks;

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
public class ShowTasks extends Command {
    private final TaskService taskService;

    @Autowired
    public ShowTasks(TaskService taskService) {
        super("show_tasks", "show all tasks in beauty table view", 0);
        this.taskService = taskService;
    }

    @Override
    public String execute() {
        isArgQuantityCorrect();
        List<TaskEntity> taskEntityList = taskService.getAll();
        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(TaskDTO.toDTO(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
