package ru.task_manager.exceptions;

public class IncorrectArgsQuantityException extends RuntimeException{
    public IncorrectArgsQuantityException(String commandName, String info){
        super(String.format("Incorrect quantity of arguments! Unable to run command '%s' with <%s>!",
                commandName, info));
    }
}
