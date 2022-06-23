package commands.impl;

import commands.Command;
import tasks.TaskManager;

public class Show implements Command {
    private final TaskManager taskManager;

    public Show(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return taskManager.showTask();
    }
}
