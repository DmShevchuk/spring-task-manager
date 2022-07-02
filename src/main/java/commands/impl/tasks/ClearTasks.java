package commands.impl.tasks;

import commands.Command;
import org.springframework.stereotype.Component;
import services.TaskService;

@Component
public class ClearTasks extends Command {
    private final TaskService taskService;

    public ClearTasks(TaskService taskService) {
        super("clear_tasks", "|| clear collection with tasks", 0);
        this.taskService = taskService;
    }

    @Override
    public String execute() {
        taskService.deleteAll();
        return "Collection of tasks was clear successfully!";
    }
}
