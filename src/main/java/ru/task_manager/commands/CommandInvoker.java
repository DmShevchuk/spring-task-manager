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

    public String invoke(String[] args) {
        try {
            String commandName = args[0];
            return commandFactory.getCommand(
                    commandName,
                    Arrays.copyOfRange(args, 1, args.length))
                    .execute();
        } catch (IncorrectCommandException |
                CommandExecutionException |
                IncorrectArgsQuantityException |
                FieldParseException |
                EntityNotFoundException |
                UserAlreadyExistsException e) {
            return e.getMessage();
        }
    }
}
