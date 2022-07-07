package ru.task_manager.commands;

import org.springframework.stereotype.Component;
import ru.task_manager.exceptions.IncorrectCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, хранящий в себе все доступные команды
 */
@Component
public class CommandFactory {
    private final Map<String, Command> commandHashMap = new HashMap<>();

    public CommandFactory(List<Command> commandList){
        for(Command command: commandList){
            commandHashMap.put(command.getName(), command);
        }
    }

    public Command getCommand(String command) throws IncorrectCommandException {
        if (commandHashMap.containsKey(command)) {
            return commandHashMap.get(command);
        }
        throw new IncorrectCommandException("Unable to recognize command '" + command + "'!");
    }
}
