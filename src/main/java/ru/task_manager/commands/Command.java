package ru.task_manager.commands;

import ru.task_manager.exceptions.*;

/**
 * Абстрактный класс для все команд
 */
public abstract class Command {
    public abstract String execute() throws CommandExecutionException, UserNotFoundException,
            UserAlreadyExistsException, TaskNotFoundException;
}
