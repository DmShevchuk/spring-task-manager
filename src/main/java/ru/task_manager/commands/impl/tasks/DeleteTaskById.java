package ru.task_manager.commands.impl.tasks;

import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import ru.task_manager.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;
import ru.task_manager.utils.InputParser;

/**
 * Класс, реализующий функционал удаления задачи по id
 * */
@Component
public class DeleteTaskById extends Command {
    private final TaskService taskService;
    private final int TASK_ID_INDEX = 0;

    public DeleteTaskById(TaskService taskService) {
        super("delete_task_by_id", "delete task by id", 1);
        this.taskService = taskService;
    }

    @Override
    public String execute() throws TaskNotFoundException {
        isArgQuantityCorrect();
        long taskId = new InputParser().parseLong(args[TASK_ID_INDEX]);
        taskService.delete(taskId);
        return String.format("Task with id=%d was deleted successfully!", taskId);
    }
}
