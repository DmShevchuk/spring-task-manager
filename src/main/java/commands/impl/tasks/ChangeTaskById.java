package commands.impl.tasks;

import commands.Command;
import entities.TaskEntity;
import models.Task;
import exceptions.IncorrectArgsQuantityException;
import exceptions.TaskNotFoundException;
import exceptions.UserNotFoundException;
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
        super("change_task", "||change task: task id, title, description, deadline, type, user id", 6);
        this.taskService = taskService;
        this.taskFactory = taskFactory;
    }

    @Override
    public String execute() throws TaskNotFoundException, UserNotFoundException {
        int taskIdIdx = 0;
        int userIdxIdx = 5;

        if (args.length != argsQuantity) {
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }
        InputParser inputParser = new InputParser();

        long taskId = inputParser.parseLong(args[taskIdIdx]);

        TaskEntity taskEntity = taskFactory.updateTaskEntity(taskService.getById(taskId), args);

        Long userId = inputParser.parseLong(args[userIdxIdx]);

        if (!userId.equals(taskEntity.getUser().getId())) {
            return Task.toModel(taskService.update(taskEntity, userId)).toString();
        }
        return Task.toModel(taskService.update(taskEntity)).toString();
    }
}
