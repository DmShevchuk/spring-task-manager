package ru.task_manager.commands;

import ru.task_manager.exceptions.CommandExecutionException;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import ru.task_manager.exceptions.UserNotFoundException;
import lombok.Getter;
import lombok.Setter;

/**
 * Абстрактный класс для все команд
 * */
public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String info;
    protected final int argsQuantity;
    @Setter
    protected String[] args = new String[]{};

    public Command(String name, String info, int argsQuantity) {
        this.name = name;
        this.info = info;
        this.argsQuantity = argsQuantity;
    }

    public abstract String execute() throws CommandExecutionException, UserNotFoundException,
            UserAlreadyExistsException, TaskNotFoundException;


    public void resetArgs(){
        args = new String[]{};
    }
}
