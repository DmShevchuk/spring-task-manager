package commands.impl.tasks;

import commands.Command;
import tasks.TaskManager;

/**
 * Класс выводящий список задач в консоль
 * */
public class ShowTasks extends Command {
    private final TaskManager taskManager;

    public ShowTasks(TaskManager taskManager) {
        super("show_tasks", "|| show all tasks in beauty table view", 0);
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return taskManager.showTask();
    }
}
