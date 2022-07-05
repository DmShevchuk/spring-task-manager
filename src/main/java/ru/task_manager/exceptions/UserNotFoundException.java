package ru.task_manager.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id) {
        super(String.format("User with id=%s does not exist!", id));
    }
}
