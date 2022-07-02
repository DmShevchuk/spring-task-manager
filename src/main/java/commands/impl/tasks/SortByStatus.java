package commands.impl.tasks;

import commands.Command;
import entities.TaskEntity;
import exceptions.IncorrectArgsQuantityException;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TaskService;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, реализующий функционал сортировки по статусу задачи
 */
@Component
public class SortByStatus extends Command {
    private final TaskService taskService;

    @Autowired
    public SortByStatus(TaskService taskService) {
        super("sort_by_status", "|| sort all tasks by status", 0);
        this.taskService = taskService;
    }

    @Override
    public String execute() {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        List<TaskEntity> taskEntityList = taskService.getAll();
        if (taskEntityList.size() == 0){
            return "Collection of tasks is empty!";
        }
        taskEntityList.sort(Comparator.comparing(TaskEntity::getType));
        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(Task.toModel(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
