package commands.impl;

import commands.Command;
import exceptions.IncorrectTaskIdException;
import tasks.TaskManager;
import tasks.TaskType;

public class ChangeType implements Command {
    private final TaskManager taskManager;
    private String additionalArgs;

    public ChangeType(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void setAdditionalArgs(String args){
        this.additionalArgs = args.trim();
    }

    @Override
    public String execute() {
        int id;
        TaskType type;
        try {
            id = Integer.parseInt(additionalArgs.split("\\s")[0].trim());
            type = TaskType.getByString(additionalArgs.split("\\s")[1].trim());
            taskManager.changeTaskType(id, type);
        }catch (IndexOutOfBoundsException | ClassCastException | IncorrectTaskIdException e){
            return "Unable to change type in '" + additionalArgs + "'!\n" + e.getMessage();
        }
        return "Type was successfully changed!";
    }
}
