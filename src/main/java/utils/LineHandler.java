package utils;

import com.opencsv.CSVParser;
import commands.Command;
import commands.CommandFactory;
import exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;


/**
 * Класс для парсинга ввода пользователя и вызова команд
 */
@Component
@RequiredArgsConstructor
public class LineHandler {
    private final CommandFactory commandFactory;

    public String parse(String line) {
        try {
            CSVParser csvParser = new CSVParser();
            String[] fields = csvParser.parseLine(line);

            // Удаление пробелов между полями
            for (int i = 0; i < fields.length; i++){
                fields[i] = fields[i].trim();
            }

            Command command = commandFactory.getCommand(fields[0]);

            if (fields.length > 1){
                command.setArgs(Arrays.copyOfRange(fields, 1, fields.length));
            }
            return command.execute();

        } catch (IncorrectCommandException |
                CommandExecutionException |
                IncorrectArgsQuantityException |
                FieldParseException |
                UserNotFoundException|
                UserAlreadyExistsException |
                TaskNotFoundException |
                IOException e) {
            return e.getMessage();
        }
    }
}
