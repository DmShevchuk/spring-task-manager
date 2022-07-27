package ru.task_manager.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String id) {
        super(String.format("Project with id=%s does not exist!", id));
    }
}
