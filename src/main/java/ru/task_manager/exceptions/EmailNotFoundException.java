package ru.task_manager.exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email) {
        super(String.format("User with email '%s' doesn't exists!", email));
    }
}