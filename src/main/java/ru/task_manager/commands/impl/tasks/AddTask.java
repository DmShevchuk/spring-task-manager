package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.services.TaskService;

/**
 * Класс, реализующий функционал добавления новой задачи
 **/
@RequiredArgsConstructor
public class AddTask implements Command {
    private final TaskService taskService;
    private final TaskEntity taskEntity;
    private final Long ownerId;

    @Override
    public String execute() throws FieldParseException, EntityNotFoundException {
        taskService.add(taskEntity, ownerId);
        return "Task was added successfully!";
    }
}
