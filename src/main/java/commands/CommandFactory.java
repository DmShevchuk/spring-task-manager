package commands;

import commands.impl.Add;
import commands.impl.ChangeType;
import commands.impl.Show;
import commands.impl.SortByStatus;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;
import users.UsersManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final TaskManager taskManager;
    private final UsersManager usersManager;
    private final Map<String, Command> commandHashMap = new HashMap<>();

    public CommandFactory(TaskManager taskManager, UsersManager usersManager) {
        this.taskManager = taskManager;
        this.usersManager = usersManager;
        initCommandHashMap();
    }

    public Command getCommand(String command) throws IncorrectCommandException {
        String line = command.split("\\s")[0].trim();
        if (commandHashMap.containsKey(line)){
            Command cmd = commandHashMap.get(line);
            int len = command.split("\\s").length;
            if(len > 1){
                String[] array = Arrays.copyOfRange(command.split("\\s"), 1, len);
                StringBuilder stringBuilder = new StringBuilder();
                for (String s: array){
                    stringBuilder.append(s).append(" ");
                }
                cmd.setAdditionalArgs(stringBuilder.toString().trim());
            }
            return commandHashMap.get(line);
        }
        throw new IncorrectCommandException("Unable to recognize command '" + line + "'!");
    }

    public void initCommandHashMap(){
        commandHashMap.put("add", new Add(taskManager));
        commandHashMap.put("change_type", new ChangeType(taskManager));
        commandHashMap.put("show", new Show(taskManager));
        commandHashMap.put("sort", new SortByStatus(taskManager));
    }


}
