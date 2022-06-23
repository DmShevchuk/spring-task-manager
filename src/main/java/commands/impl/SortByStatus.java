package commands.impl;

import commands.Command;
import tasks.TaskManager;

/**
 * Класс, реализующий функционал сортировки по статусу задачи
 * */
public class SortByStatus implements Command {
    private final TaskManager taskManager;

    public SortByStatus(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        taskManager.sort();
        return taskManager.showTask();
    }
}
