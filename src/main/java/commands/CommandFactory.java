package commands;

import commands.impl.Exit;
import commands.impl.Help;
import commands.impl.Save;
import commands.impl.tasks.*;
import commands.impl.users.AddUser;
import commands.impl.users.ClearUsers;
import commands.impl.users.ShowUsers;
import exceptions.IncorrectCommandException;
import tasks.TaskManager;
import users.UsersManager;
import utils.CommandLine;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final TaskManager taskManager;
    private final UsersManager usersManager;
    private final CommandLine commandLine;
    private final Map<String, Command> commandHashMap = new HashMap<>();

    public CommandFactory(UsersManager usersManager, TaskManager taskManager, CommandLine commandLine) {
        this.usersManager = usersManager;
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

    // Получение информации по всем командам имя: информация
    private Map<String, String> getCommandsInfo(){
        Map<String, String> infoMap = new HashMap<>();
        for(String key: commandHashMap.keySet()){
            infoMap.put(key, commandHashMap.get(key).getInfo());
        }
        return infoMap;
    }

    // Установка всех доступных команд
    private void initCommandHashMap() {
        commandHashMap.put("add_task", new AddTask(taskManager, commandLine));
        commandHashMap.put("change_task_by_id", new ChangeTaskById(taskManager, commandLine));
        commandHashMap.put("show_tasks", new ShowTasks(taskManager));
        commandHashMap.put("sort_by_status", new SortByStatus(taskManager));
        commandHashMap.put("clear_tasks", new ClearTasks(taskManager));
        commandHashMap.put("delete_task_by_id", new DeleteTaskById(taskManager));
        commandHashMap.put("add_user", new AddUser(usersManager, commandLine));
        commandHashMap.put("clear_users", new ClearUsers(usersManager));
        commandHashMap.put("show_users", new ShowUsers(usersManager));
        commandHashMap.put("help", new Help(getCommandsInfo()));
        commandHashMap.put("save", new Save(usersManager, taskManager));
        commandHashMap.put("exit", new Exit());
    }
}
