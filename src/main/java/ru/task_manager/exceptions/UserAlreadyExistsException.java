package ru.task_manager.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String name) {
        super(String.format("User with name '%s' already exists!", name));
    }
}
