package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DeleteTaskById extends Command {
    private final TaskService taskService;
    private final int TASK_ID_INDEX = 0;

    @Override
    public String execute(String[] args) throws TaskNotFoundException {
        isArgQuantityCorrect();
        long taskId = new InputParser().parseLong(args[TASK_ID_INDEX]);
        taskService.delete(taskId);
        return String.format("Task with id=%d was deleted successfully!", taskId);
    }

    @Override
    public String getName() {
        return "delete_task_by_id";
    }

    @Override
    public String getInfo() {
        return "delete task by id";
    }

    @Override
    public int getArgsQuantity() {
        return 1;
    }
}
