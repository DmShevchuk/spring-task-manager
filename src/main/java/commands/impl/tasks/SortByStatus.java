package commands.impl.tasks;

import commands.Command;
import tasks.Task;
import tasks.TaskManager;
import utils.TaskTableView;

import java.util.List;

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
        List<Task> lst = taskManager.getTasks();
        if (lst.size() == 0) {
            return "Collection is empty!";
        }
        return new TaskTableView(lst, List.of("id", "title", "description", "owner id", "deadline", "status"))
                .printCollection();
    }
}
