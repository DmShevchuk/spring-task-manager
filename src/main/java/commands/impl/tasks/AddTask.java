package commands.impl.tasks;

import commands.Command;
import entities.TaskEntity;
import exceptions.FieldParseException;
import exceptions.IncorrectArgsQuantityException;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TaskService;
import tasks.TaskFactory;
import utils.InputParser;


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
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        TaskEntity taskEntity = taskFactory.getTaskEntity(args);

        long ownerId = new InputParser().parseLong(args[4]);

        taskService.create(taskEntity, ownerId);
        return "Task was added successfully!";
    }
}
