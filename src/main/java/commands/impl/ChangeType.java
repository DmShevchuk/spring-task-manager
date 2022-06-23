package commands.impl;

import commands.Command;
import exceptions.IncorrectTaskIdException;
import tasks.TaskManager;
import tasks.TaskType;
import utils.CommandLine;

public class ChangeType implements Command {
    private final TaskManager taskManager;
    private final CommandLine commandLine;

    public ChangeType(TaskManager taskManager, CommandLine commandLine) {
        this.taskManager = taskManager;
        this.commandLine = commandLine;
    }

    @Override
    public String execute() {
        int id;
        TaskType type;
        while (true) {
            System.out.print("Enter task id:");
            String inputLine = commandLine.getLine();
            try {
                id = Integer.parseInt(inputLine);
                break;
            } catch (NumberFormatException | ClassCastException e) {
                System.out.println("Unable to get id from '" + inputLine + "'!");
            }
        }

        System.out.print("Enter new task status:");
        type = TaskType.getByString(commandLine.getLine());

        try {
            taskManager.changeTaskType(id, type);
        } catch (IncorrectTaskIdException e) {
            return "Task with id=" + id + " doesn't exist!";
        }
        return "Type was successfully changed!";
    }
}
