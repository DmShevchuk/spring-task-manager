package ru.task_manager.commands;

import lombok.Setter;
import ru.task_manager.exceptions.*;

/**
 * Абстрактный класс для все команд
 */
public abstract class Command {
    @Setter
    protected String[] args = new String[]{};

    public Command() {}

    public abstract String execute(String[] args) throws CommandExecutionException, UserNotFoundException,
            UserAlreadyExistsException, TaskNotFoundException;

    protected void isArgQuantityCorrect() {
        if (args.length != getArgsQuantity()) {
            throw new IncorrectArgsQuantityException(getArgsQuantity(), args.length);
        }
    }
    public void resetArgs() {
        args = new String[]{};
    }

    public abstract String getName();
    public abstract String getInfo();
    public abstract int getArgsQuantity();
}
