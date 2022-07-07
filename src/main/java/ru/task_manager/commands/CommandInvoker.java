package ru.task_manager.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.exceptions.*;

import java.util.Arrays;

/**
 * Класс для вызова команд {@link Command}
 * */
@Component
@RequiredArgsConstructor
public class CommandInvoker {
    private final CommandFactory commandFactory;
    private final int COMMAND_NAME_INDEX = 0;

    public String invoke(String[] args) {
        try {
            Command command = commandFactory.getCommand(args[COMMAND_NAME_INDEX]);
            command.resetArgs();
            if (args.length > 1) {
                command.setArgs(Arrays.copyOfRange(args, 1, args.length));
            }
            return command.execute(args);
        } catch (IncorrectCommandException |
                CommandExecutionException |
                IncorrectArgsQuantityException |
                FieldParseException |
                UserNotFoundException |
                UserAlreadyExistsException |
                TaskNotFoundException e) {
            return e.getMessage();
        }
    }
}
