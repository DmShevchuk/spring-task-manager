package ru.task_manager.utils;

import com.opencsv.CSVParser;
import org.springframework.stereotype.Component;
import ru.task_manager.factories.TaskType;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Класс для парсинга ввода пользователя
 */
@Component
public class LineHandler {
    public String[] parse(String line) throws IOException {
        CSVParser csvParser = new CSVParser();
        try {
            String[] fields = csvParser.parseLine(line);
            // Удаление пробелов между полями
            for (int i = 0; i < fields.length; i++) {
                fields[i] = fields[i].trim();
            }
            return fields;
        } catch (NullPointerException | IOException e) {
            throw new IOException(String.format("Unable to get data from line='%s'!", line));
        }
    }

    public TaskType parseTaskType(String value) {
        if (value == null) {
            return null;
        }
        return TaskType.getByString(value);
    }

    public Date parseDate(String value){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formatter.parse(value);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}
