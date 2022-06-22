package commands.impl;

import commands.Command;
import tasks.TaskManager;

public class SortByStatus implements Command {
    private final TaskManager taskManager;
    private String additionalArgs;

    public SortByStatus(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        taskManager.sort();
        return taskManager.showTask();
    }

    @Override
    public void setAdditionalArgs(String string) {
        this.additionalArgs = string;
    }
}
