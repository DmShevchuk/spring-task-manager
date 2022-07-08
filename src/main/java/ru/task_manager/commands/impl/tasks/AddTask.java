package ru.task_manager.commands.impl.tasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;
import ru.task_manager.factories.TaskFactory;
import ru.task_manager.utils.InputParser;


/**
 * Класс, реализующий функционал добавления новой задачи
 **/
@RequiredArgsConstructor
public class AddTask extends Command {
    private final TaskService taskService;
    private final TaskEntity taskEntity;
    private final Long ownerId;

    @Override
    public String execute() throws FieldParseException, UserNotFoundException  {
        taskService.add(taskEntity, ownerId);
        return "Task was added successfully!";
    }
}
