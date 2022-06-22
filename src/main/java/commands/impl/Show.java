package commands.impl;

import commands.Command;
import tasks.TaskManager;

public class Show implements Command {
    private final TaskManager taskManager;
    private String additionalArgs;

    public Show(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        return taskManager.showTask();
    }

    @Override
    public void setAdditionalArgs(String string) {
        this.additionalArgs = string;
    }
}
