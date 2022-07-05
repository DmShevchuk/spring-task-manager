package ru.task_manager.commands.impl.tasks;

import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import ru.task_manager.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;
import ru.task_manager.factories.TaskFactory;
import ru.task_manager.utils.InputParser;


/**
 * Класс, реализующий функционал добавления новой задачи
 **/
@Component
public class AddTask extends Command {
    private final TaskService taskService;
    private final TaskFactory taskFactory;

    @Autowired
    public AddTask(TaskService taskService, TaskFactory taskFactory) {
        super("add_task", "|| add new task: title, description, deadline, type, user id", 5);
        this.taskService = taskService;
        this.taskFactory = taskFactory;
    }

    @Override
    public String execute() throws FieldParseException, UserNotFoundException {
        int ownerIdIndex = 4;
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        TaskEntity taskEntity = taskFactory.getTaskEntity(args);

        long ownerId = new InputParser().parseLong(args[ownerIdIndex]);

        taskService.create(taskEntity, ownerId);
        return "Task was added successfully!";
    }
}
