package ru.task_manager.utils;

import com.opencsv.CSVParser;
import ru.task_manager.commands.Command;
import ru.task_manager.commands.CommandFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.exceptions.*;

import java.io.IOException;
import java.util.Arrays;


/**
 * Класс для парсинга ввода пользователя и вызова команд
 */
@Component
@RequiredArgsConstructor
public class LineHandler {
    private final CommandFactory commandFactory;
    private final int COMMAND_NAME_INDEX = 0;

    public String parse(String line) {
        try {
            CSVParser csvParser = new CSVParser();
            String[] fields = csvParser.parseLine(line);

            // Удаление пробелов между полями
            for (int i = 0; i < fields.length; i++){
                fields[i] = fields[i].trim();
            }

            Command command = commandFactory.getCommand(fields[COMMAND_NAME_INDEX]);
            command.resetArgs();
            if (fields.length > 1){
                command.setArgs(Arrays.copyOfRange(fields, 1, fields.length));
            }
            return command.execute();

        } catch (IncorrectCommandException |
                CommandExecutionException |
                IncorrectArgsQuantityException |
                FieldParseException |
                UserNotFoundException |
                UserAlreadyExistsException |
                TaskNotFoundException |
                IOException e) {
            return e.getMessage();
        }
    }
}
