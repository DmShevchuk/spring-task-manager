package commands.impl.tasks;

import commands.Command;
import exceptions.IncorrectArgsQuantityException;
import exceptions.TaskNotFoundException;
import org.springframework.stereotype.Component;
import services.TaskService;
import utils.InputParser;

@Component
public class DeleteTaskById extends Command {
    private final TaskService taskService;

    public DeleteTaskById(TaskService taskService) {
        super("delete_task_by_id", "|| {id} delete task by id", 1);
        this.taskService = taskService;
    }

    @Override
    public String execute() throws TaskNotFoundException {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        long taskId = new InputParser().parseLong(args[0]);
        taskService.delete(taskId);
        return String.format("Task with id=%d was deleted successfully!", taskId);
    }
}
