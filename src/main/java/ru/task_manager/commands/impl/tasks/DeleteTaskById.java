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

    public DeleteTaskById(TaskService taskService) {
        super("delete_task_by_id", "|| {id} delete task by id", 1);
        this.taskService = taskService;
    }

    @Override
    public String execute() throws TaskNotFoundException {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        long taskId = new InputParser().parseLong(args[0]);
        taskService.delete(taskId);
        return String.format("Task with id=%d was deleted successfully!", taskId);
    }
}
