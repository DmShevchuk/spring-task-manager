package utils;

import exceptions.FieldParseException;
import org.springframework.stereotype.Component;
import tasks.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, отвечающий за парсинг данных из ввода пользователя
 */
@Component
public class InputParser {
    public long parseLong(String value) throws FieldParseException {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException | ClassCastException e) {
            throw new FieldParseException("Unable to get id from '" + value + "'!");
        }
    }

    public String parseString(String value) throws FieldParseException {
        if (value.length() != 0) {
            return value;
        }
        throw new FieldParseException("Unable to read field from empty string!");
    }

    public Date parseDate(String value) throws FieldParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            throw new FieldParseException("Unable to get date from '" + value + "'!");
        }
    }

    /**
     * Метод #getByString в {@link TaskType} возвращает TaskType.NEW по умолчанию
     * */
    public TaskType parseTaskType(String value) {
        return TaskType.getByString(value);
    }
}
