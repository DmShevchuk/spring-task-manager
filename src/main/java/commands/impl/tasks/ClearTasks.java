package commands.impl.tasks;

import commands.Command;
import tasks.TaskManager;

public class ClearTasks extends Command {
    private final TaskManager taskManager;

    public ClearTasks(TaskManager taskManager){
        super("clear_tasks", "|| clear collection with tasks", 0);
        this.taskManager = taskManager;
    }

    @Override
    public String execute() {
        taskManager.clearCollection();
        return "Collection was clear successfully!";
    }
}
