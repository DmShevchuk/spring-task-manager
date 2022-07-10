package ru.task_manager.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link TaskEntity} из ввода пользователя
 */
@Component
@RequiredArgsConstructor
public class TaskFactory {
    private final InputParser inputParser;

    public TaskEntity getTaskEntity(String[] fields) throws FieldParseException {
        return new TaskEntity.Builder()
                .setTitle(inputParser.parseString(fields[0]))
                .setDescription(inputParser.parseString(fields[1]))
                .setDeadline(inputParser.parseDate(fields[2]))
                .setTaskType(inputParser.parseTaskType(fields[3]))
                .build();
    }

    public TaskEntity updateTaskEntity(String[] fields) throws FieldParseException {
        return new TaskEntity.Builder()
                .setId(inputParser.parseLong(fields[0]))
                .setTitle(inputParser.parseString(fields[1]))
                .setDescription(inputParser.parseString(fields[2]))
                .setDeadline(inputParser.parseDate(fields[3]))
                .setTaskType(inputParser.parseTaskType(fields[4]))
                .build();
    }
}
