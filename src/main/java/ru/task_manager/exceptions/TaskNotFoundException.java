package ru.task_manager.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String id) {
        super(String.format("Task with id=%s does not exist!", id));
    }
}
