package commands;

import commands.impl.Add;
import commands.impl.ChangeType;
import commands.impl.Show;
import commands.impl.SortByStatus;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;
import utils.CommandLine;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final TaskManager taskManager;
    private final CommandLine commandLine;
    private final Map<String, Command> commandHashMap = new HashMap<>();

    public CommandFactory(TaskManager taskManager, CommandLine commandLine) {
        this.taskManager = taskManager;
        this.commandLine = commandLine;
        initCommandHashMap();
    }

    public Command getCommand(String command) throws IncorrectCommandException {
        String line = command.split("\\s")[0].trim();
        if (commandHashMap.containsKey(line)) {
            return commandHashMap.get(line);
        }
        throw new IncorrectCommandException("Unable to recognize command '" + line + "'!");
    }

    public void initCommandHashMap() {
        commandHashMap.put("add", new Add(taskManager, commandLine));
        commandHashMap.put("change_type", new ChangeType(taskManager, commandLine));
        commandHashMap.put("show", new Show(taskManager));
        commandHashMap.put("sort", new SortByStatus(taskManager));
    }


}
