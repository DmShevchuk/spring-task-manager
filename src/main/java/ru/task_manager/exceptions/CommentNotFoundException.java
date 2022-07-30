package ru.task_manager.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String commentId) {
        super(String.format("Comment with id=%s does not exist!", commentId));
    }
}
