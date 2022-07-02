package commands.impl.tasks;

import commands.Command;
import exceptions.CommandExecutionException;
import exceptions.IncorrectArgsQuantityException;
import exceptions.TaskNotFoundException;
import org.springframework.stereotype.Component;
import services.TaskService;
import tasks.TaskManager;
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
        String[] args = getArgsAsArray();
        resetArgs();
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        long taskId = new InputParser().parseInteger(args[0]);
        taskService.delete(taskId);
        return String.format("Task with id=%d was deleted successfully!", taskId);
    }
}
