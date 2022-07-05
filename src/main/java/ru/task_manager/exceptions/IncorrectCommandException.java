package ru.task_manager.exceptions;

public class IncorrectCommandException extends RuntimeException {
    public IncorrectCommandException(String message) {
        super(message);
    }
}
