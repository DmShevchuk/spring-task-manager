package commands.impl;

import commands.Command;
import tasks.Task;
import tasks.TaskManager;
import tasks.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Add implements Command {
    private final TaskManager taskManager;
    private String additionalArgs;

    public Add(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void setAdditionalArgs(String args){
        this.additionalArgs = args;
    }

    @Override
    public String execute() {
        String[] line = additionalArgs.trim().split("\\s");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(additionalArgs);
        try {
            taskManager.add(new Task(
                    // id
                    Integer.parseInt(line[0].trim()),
                    // title
                    line[1].trim(),
                    // description
                    line[2].trim(),
                    // owner id
                    Integer.parseInt(line[3].trim()),
                    // deadline
                    formatter.parse(line[4].trim()),
                    // task type
                    TaskType.getByString(line[5].trim())
            ));
        }catch (IndexOutOfBoundsException | ClassCastException | ParseException e){
            return "Unable to read string " + Arrays.toString(line) + "!";
        }
        return "Task was added successfully!";
    }
}
