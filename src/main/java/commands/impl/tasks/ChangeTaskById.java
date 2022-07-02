package commands.impl.tasks;

import commands.Command;
import entities.TaskEntity;
import exceptions.*;
import models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.TaskService;
import tasks.TaskFactory;
import utils.InputParser;

/**
 * Класс, реализующий функционал обновления задачи
 **/
@Component
public class ChangeTaskById extends Command {
    private final TaskService taskService;
    private final TaskFactory taskFactory;

    @Autowired
    public ChangeTaskById(TaskService taskService, TaskFactory taskFactory) {
        super("change_task", "|| {id} change task", 6);
        this.taskService = taskService;
        this.taskFactory = taskFactory;
    }

    @Override
    public String execute() throws CommandExecutionException, FieldParseException, TaskNotFoundException, UserNotFoundException {
        if (args.length != argsQuantity) {
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }
        InputParser inputParser = new InputParser();

        long taskId = inputParser.parseLong(args[0]);

        TaskEntity taskEntity = taskFactory.updateTaskEntity(taskService.getById(taskId), args);

        Long userId = inputParser.parseLong(args[5]);

        if (!userId.equals(taskEntity.getUser().getId())) {
            return Task.toModel(taskService.update(taskEntity, userId)).toString();
        }
        return Task.toModel(taskService.update(taskEntity)).toString();
    }
}
