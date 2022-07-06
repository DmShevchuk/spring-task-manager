package ru.task_manager.utils;

import com.opencsv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Класс для парсинга ввода пользователя
 */
@Component
public class LineHandler {
    public String[] parse(String line) throws IOException {
        CSVParser csvParser = new CSVParser();
        String[] fields = csvParser.parseLine(line);

        // Удаление пробелов между полями
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].trim();
        }
        return fields;
    }
}
