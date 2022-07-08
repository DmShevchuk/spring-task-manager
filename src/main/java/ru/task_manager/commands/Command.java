package ru.task_manager.commands;

import ru.task_manager.exceptions.CommandExecutionException;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import ru.task_manager.exceptions.UserNotFoundException;

/**
 * Абстрактный класс для все команд
 */
public interface Command {
    String execute() throws CommandExecutionException,
            UserNotFoundException,
            UserAlreadyExistsException,
            TaskNotFoundException;
}
