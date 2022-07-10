package ru.task_manager.exceptions;

public class IncorrectCommandException extends RuntimeException {
    public IncorrectCommandException(String message) {
        super(String.format("Command with name '%s' does not exist!", message));
    }
}
