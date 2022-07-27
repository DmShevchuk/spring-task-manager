package ru.task_manager.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String id) {
        super(String.format("Comment with id=%s does not exist!", id));
    }
}
