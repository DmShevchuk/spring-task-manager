package ru.task_manager.commands.impl.tasks;

import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;

/**
 *  Класс, реализующий функционал удаления всех задач
 * */
@Component
public class ClearTasks extends Command {
    private final TaskService taskService;

    public ClearTasks(TaskService taskService) {
        super("clear_tasks", "|| clear collection with tasks", 0);
        this.taskService = taskService;
    }

    @Override
    public String execute() {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        taskService.deleteAll();
        return "Collection of tasks was clear successfully!";
    }
}
