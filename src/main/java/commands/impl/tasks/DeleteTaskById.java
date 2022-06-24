package commands.impl.tasks;

import commands.Command;
import exceptions.CommandExecutionException;
import tasks.TaskManager;

public class DeleteTaskById extends Command {
    private final TaskManager taskManager;

    public DeleteTaskById(TaskManager taskManager) {
        super("delete_task_by_id", "|| {id} delete task by id", 1);
        this.taskManager = taskManager;
    }

    @Override
    public String execute() throws CommandExecutionException {
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Id=" + arg + " is not integer!");
        }

        boolean isIdExist = taskManager.isIdPresent(id);
        if (!isIdExist) {
            throw new CommandExecutionException("Id=" + id + " does not exist!");
        }
        taskManager.deleteById(id);
        return "Task with id=" + id + " was deleted!";
    }
}
