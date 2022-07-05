package ru.task_manager.exceptions;

public class CommandExecutionException extends RuntimeException{
    public CommandExecutionException(String message){
        super(message);
    }
}
