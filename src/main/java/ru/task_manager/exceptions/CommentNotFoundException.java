package ru.task_manager.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String commentId, String taskId) {
        super(String.format("Comment with id=%s and taskId=%s does not exist!", commentId, taskId));
    }
}
