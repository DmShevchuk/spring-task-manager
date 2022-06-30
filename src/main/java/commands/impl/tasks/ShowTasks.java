package commands.impl.tasks;

import commands.Command;
import tasks.Task;
import tasks.TaskManager;
import utils.TaskTableView;

import java.util.List;

/**
 * Класс выводящий список задач в консоль
 */
public class ShowTasks extends Command {
    private final TaskManager taskManager;

    public ShowTasks(TaskManager taskManager) {
        super("show_tasks", "|| show all tasks in beauty table view", 0);
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        List<Task> lst = taskManager.getTasks();
        if (lst.size() == 0) {
            return "Collection is empty!";
        }
        return new TaskTableView(lst, List.of("id", "title", "description", "owner id", "deadline", "status"))
                .printCollection();
    }
}
