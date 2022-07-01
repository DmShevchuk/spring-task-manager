package exceptions;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String id) {
        super(String.format("Task with id=%s does not exist!", id));
    }
}
