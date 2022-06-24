package commands.impl.tasks;

import commands.Command;
import tasks.TaskManager;

/**
 * Класс, реализующий функционал сортировки по статусу задачи
 */
public class SortByStatus extends Command {
    private final TaskManager taskManager;

    public SortByStatus(TaskManager taskManager) {
        super("sort_by_status", "|| sort all tasks by status", 0);
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        taskManager.sort();
        return taskManager.showTask();
    }
}
