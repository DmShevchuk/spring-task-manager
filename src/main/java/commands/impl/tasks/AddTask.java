package commands.impl.tasks;

import commands.Command;
import tasks.TaskFactory;
import tasks.TaskManager;
import utils.CommandLine;


/**
 * Класс, реализующий функционал добавления задачи в список
 **/
public class AddTask extends Command {
    private final TaskManager taskManager;
    private final CommandLine commandLine;

    public AddTask(TaskManager taskManager, CommandLine commandLine) {
        super("add_task", "|| add new task", 0);
        this.taskManager = taskManager;
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        taskManager.add(new TaskFactory(commandLine).getTask());
        return "Task was added successfully!";
    }
}
