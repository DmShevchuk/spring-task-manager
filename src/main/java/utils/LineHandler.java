package utils;

import commands.Command;
import commands.CommandFactory;
import exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * Класс для парсинга ввода пользователя и вызова команд
 */
@Component
@RequiredArgsConstructor
public class LineHandler {
    private final CommandFactory commandFactory;

    public String parse(String line) {
        try {
            Command command = commandFactory.getCommand(line);

            int firstWhiteSpaceIdx = line.indexOf(' ');

            if (firstWhiteSpaceIdx != -1) {
                command.setArg(line.substring(firstWhiteSpaceIdx).trim());
            }
            return command.execute();

        } catch (IncorrectCommandException |
                CommandExecutionException |
                IncorrectArgsQuantityException |
                FieldParseException|
                UserNotFoundException|
                UserAlreadyExistsException |
                TaskNotFoundException e) {
            return e.getMessage();
        }
    }
}
