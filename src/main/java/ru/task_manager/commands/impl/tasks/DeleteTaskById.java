package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.services.TaskService;

/**
 * Класс, реализующий функционал удаления задачи по id
 * */
@RequiredArgsConstructor
public class DeleteTaskById implements Command {
    private final TaskService taskService;
    private final Long id;

    @Override
    public String execute() throws EntityNotFoundException {
        taskService.delete(id);
        return String.format("Task with id=%d was deleted successfully!", id);
    }
}
