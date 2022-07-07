package ru.task_manager.commands.impl.tasks;

import lombok.Getter;
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
    @Getter
    private final String name = "show_tasks";
    @Getter
    private final String info = "show all tasks in beauty table view";
    @Getter
    private final int argsQuantity = 0;

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
}
