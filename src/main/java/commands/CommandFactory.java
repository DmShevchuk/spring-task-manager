package commands;

import commands.impl.Help;
import commands.impl.tasks.*;
import commands.impl.users.AddUser;
import commands.impl.users.ClearUsers;
import commands.impl.users.DeleteUserById;
import commands.impl.users.ShowUsers;
import exceptions.IncorrectCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandFactory {
    private final Map<String, Command> commandHashMap = new HashMap<>();
    private final AddTask addTask;
    private final AddUser addUser;
    private final ShowTasks showTasks;
    private final ShowUsers showUsers;
    private final ChangeTaskById changeTaskById;
    private final ClearTasks clearTasks;
    private final ClearUsers clearUsers;
    private final DeleteTaskById deleteTaskById;
    private final DeleteUserById deleteUserById;
    private final SortByStatus sortByStatus;
    private final Help help;

    @Autowired
    public CommandFactory(AddTask addTask,
                          AddUser addUser,
                          ShowTasks showTasks,
                          ShowUsers showUsers,
                          ChangeTaskById changeTaskById,
                          ClearTasks clearTasks,
                          ClearUsers clearUsers,
                          DeleteTaskById deleteTaskById,
                          DeleteUserById deleteUserById,
                          SortByStatus sortByStatus,
                          Help help) {
        this.addTask = addTask;
        this.addUser = addUser;
        this.showTasks = showTasks;
        this.showUsers = showUsers;
        this.changeTaskById = changeTaskById;
        this.clearTasks = clearTasks;
        this.clearUsers = clearUsers;
        this.deleteTaskById = deleteTaskById;
        this.deleteUserById = deleteUserById;
        this.sortByStatus = sortByStatus;
        this.help = help;
        initCommandHashMap();
    }

    public Command getCommand(String command) throws IncorrectCommandException {
        String line = command.split("\\s")[0].trim();
        System.out.println(line);
        if (commandHashMap.containsKey(line)) {
            return commandHashMap.get(line);
        }
        throw new IncorrectCommandException("Unable to recognize command '" + line + "'!");
    }

    // Получение информации по всем командам в формате _имя_: _информация_
    private Map<String, String> getCommandsInfo() {
        Map<String, String> infoMap = new HashMap<>();
        for (String key : commandHashMap.keySet()) {
            infoMap.put(key, commandHashMap.get(key).getInfo());
        }
        return infoMap;
    }

    // Установка всех доступных команд
    private void initCommandHashMap() {
        commandHashMap.put("add_task", addTask);
        commandHashMap.put("add_user", addUser);
        commandHashMap.put("show_tasks", showTasks);
        commandHashMap.put("show_users", showUsers);
        commandHashMap.put("change_task_by_id", changeTaskById);
        commandHashMap.put("clear_tasks", clearTasks);
        commandHashMap.put("clear_users", clearUsers);
        commandHashMap.put("delete_task_by_id", deleteTaskById);
        commandHashMap.put("delete_user_by_id", deleteUserById);
        commandHashMap.put("sort_by_status", sortByStatus);
        commandHashMap.put("help", help);


        help.initializeInfoString(getCommandsInfo());
    }
}
