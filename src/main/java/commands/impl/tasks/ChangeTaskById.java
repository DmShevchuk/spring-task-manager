package commands.impl.tasks;

import commands.Command;
import exceptions.CommandExecutionException;
import tasks.TaskFactory;
import tasks.TaskManager;

/**
 * Класс, реализующий функционал обновления статуса задачи
 **/
public class ChangeTaskById extends Command {
    private final TaskManager taskManager;

    public ChangeTaskById(TaskManager taskManager) {
        super("change_task", "|| {id} change task", 1);
        this.taskManager = taskManager;
    }

    @Override
    public String execute() throws CommandExecutionException {
        int id;

        try {
            id = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Unable to get id from '" + arg + "'!");
        }

        if (!taskManager.isIdPresent(id)) {
            throw new CommandExecutionException("Task with id=" + id + "does not exist!");
        }

        System.out.println("Task to change:\n" + taskManager.getById(id));

        //taskManager.changeTask(new TaskFactory(commandLine).getTask().id(id).build());

        return "Task successfully changed:\n" + taskManager.getById(id);
    }
}
