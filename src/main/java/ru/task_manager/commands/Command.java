package ru.task_manager.commands;

import ru.task_manager.exceptions.CommandExecutionException;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.exceptions.UserAlreadyExistsException;

/**
 * Абстрактный класс для все команд
 */
public interface Command {
    String execute() throws CommandExecutionException,
            EntityNotFoundException,
            UserAlreadyExistsException;
}
