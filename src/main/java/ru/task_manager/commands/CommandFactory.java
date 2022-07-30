package ru.task_manager.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.commands.impl.Help;
import ru.task_manager.commands.impl.tasks.*;
import ru.task_manager.commands.impl.users.*;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import ru.task_manager.exceptions.IncorrectCommandException;
import ru.task_manager.factories.TaskFactory;
import ru.task_manager.factories.TaskType;
import ru.task_manager.factories.UserFactory;
import ru.task_manager.services.TaskService;
import ru.task_manager.services.UserService;
import ru.task_manager.utils.CommandPropertiesReader;
import ru.task_manager.utils.InputParser;
import ru.task_manager.utils.LineHandler;

import java.util.*;

/**
 * Класс, хранящий в себе все доступные команды
 */
@Component
@RequiredArgsConstructor
public class CommandFactory {
    private final TaskService taskService;
    private final UserService userService;
    private final TaskFactory taskFactory;
    private final UserFactory userFactory;
    private final InputParser inputParser;
    private final CommandPropertiesReader commandPropertiesReader;

    public Command getCommand(String commandName, String[] args) {
        switch (commandName) {
            case "add_task":
                return getAddTaskCommand(args);
            case "change_task":
                return getChangeTaskCommand(args);
            case "add_user":
                return getAddUserCommand(args);
            case "clear_tasks":
                return getClearTaskCommand();
            case "delete_task_by_id":
                return getDeleteTaskCommand(args);
            case "clear_users":
                return getClearUserCommand();
            case "delete_user_by_id":
                return getDeleteUserByIdCommand(args);
            case "help":
                return getHelpCommand();
            case "find_user_with_max_task_quantity":
                return getUserWithMaxTaskCommand(args);
        }
        throw new IncorrectCommandException(commandName);
    }

    private Command getAddTaskCommand(String[] args) {
        try {
            TaskEntity taskEntity = taskFactory.getTaskEntity(args);
            long ownerId = inputParser.parseLong(args[args.length - 1]);
            return new AddTask(taskService, taskEntity, ownerId);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgsQuantityException("add_task", Arrays.toString(args));
        }
    }

    private Command getChangeTaskCommand(String[] args) {
        try {
            TaskEntity taskEntity = taskFactory.updateTaskEntity(args);
            long ownerId = inputParser.parseLong(args[args.length - 1]);
            return new ChangeTask(taskService, taskEntity, ownerId);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgsQuantityException("change_task", Arrays.toString(args));
        }
    }

    private Command getClearTaskCommand() {
        return new ClearTasks(taskService);
    }

    private Command getDeleteTaskCommand(String[] args) {
        try {
            long id = inputParser.parseLong(args[0]);
            return new DeleteTaskById(taskService, id);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgsQuantityException("delete_task_by_id", Arrays.toString(args));
        }
    }

    private Command getAddUserCommand(String[] args) {
        try {
            UserEntity userEntity = userFactory.getUser(args);
            return new AddUser(userService, userEntity);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgsQuantityException("add_user", Arrays.toString(args));
        }
    }

    private Command getClearUserCommand() {
        return new ClearUsers(userService);
    }

    private Command getDeleteUserByIdCommand(String[] args) {
        try {
            long id = inputParser.parseLong(args[0]);
            return new DeleteUserById(userService, id);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgsQuantityException("delete_task_by_id", Arrays.toString(args));
        }
    }

    private Command getHelpCommand() {
        Map<String, String> infoMap = new HashMap<>();
        Set<Object> commandNamesSet = commandPropertiesReader.getKeySet();
        for (Object key : commandNamesSet) {
            infoMap.put(key.toString(), commandPropertiesReader.getCommandInfo(key.toString()));
        }
        return new Help(infoMap);
    }

    private Command getUserWithMaxTaskCommand(String[] args){
        LineHandler lineHandler = new LineHandler();
        TaskType type = lineHandler.parseTaskType(args[0]);
        Date minDate = lineHandler.parseDate(args[1]);
        Date maxDate = lineHandler.parseDate(args[2]);
        return new FindUserWithMaxTaskQuantity(userService, type, minDate, maxDate);
    }
}
