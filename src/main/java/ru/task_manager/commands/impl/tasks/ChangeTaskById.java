package ru.task_manager.commands.impl.tasks;

import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;
import ru.task_manager.factories.TaskFactory;
import ru.task_manager.utils.InputParser;

/**
 * Класс, реализующий функционал обновления задачи
 **/
@Component
public class ChangeTaskById extends Command {
    private final TaskService taskService;
    private final TaskFactory taskFactory;
    private final int TASK_ID_INDEX = 0;
    private final int USER_ID_INDEX = 5;

    @Autowired
    public ChangeTaskById(TaskService taskService, TaskFactory taskFactory) {
        super("change_task", "change task: task id, title, description, deadline, type, user id", 6);
        this.taskService = taskService;
        this.taskFactory = taskFactory;
    }

    @Override
    public String execute() throws TaskNotFoundException, UserNotFoundException {
        isArgQuantityCorrect();
        InputParser inputParser = new InputParser();
        long taskId = inputParser.parseLong(args[TASK_ID_INDEX]);
        TaskEntity taskEntity = taskFactory.updateTaskEntity(taskService.getById(taskId), args);
        Long userId = inputParser.parseLong(args[USER_ID_INDEX]);
        if (!userId.equals(taskEntity.getUser().getId())) {
            return TaskDTO.toDTO(taskService.add(taskEntity, userId)).toString();
        }
        return TaskDTO.toDTO(taskService.update(taskEntity)).toString();
    }
}
