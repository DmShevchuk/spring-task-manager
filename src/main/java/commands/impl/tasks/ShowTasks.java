package commands.impl.tasks;

import commands.Command;
import entities.TaskEntity;
import exceptions.IncorrectArgsQuantityException;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TaskService;

import java.util.List;

/**
 * Класс выводящий список задач
 */
@Component
public class ShowTasks extends Command {
    private final TaskService taskService;

    @Autowired
    public ShowTasks(TaskService taskService) {
        super("show_tasks", "|| show all tasks in beauty table view", 0);
        this.taskService = taskService;
    }

    @Override
    public String execute() {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        List<TaskEntity> taskEntityList = taskService.getAll();
        if (taskEntityList.size() == 0) {
            return "No added tasks!";
        }

        StringBuilder totalString = new StringBuilder();
        for (TaskEntity taskEntity : taskEntityList) {
            totalString.append(Task.toModel(taskEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
