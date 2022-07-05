package tasks;

import entities.TaskEntity;
import exceptions.FieldParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link TaskEntity} из ввода пользователя
 */
@Component
@RequiredArgsConstructor
public class TaskFactory {
    private final InputParser inputParser;

    public TaskEntity getTaskEntity(String[] fields) throws FieldParseException {
        int titleIdx = 0;
        int descriptionIdx = 1;
        int deadlineIdx = 2;
        int typeIdx = 3;

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(inputParser.parseString(fields[titleIdx]));
        taskEntity.setDescription(inputParser.parseString(fields[descriptionIdx]));
        taskEntity.setDeadline(inputParser.parseDate(fields[deadlineIdx]));
        taskEntity.setType(inputParser.parseTaskType(fields[typeIdx]));

        return taskEntity;
    }

    public TaskEntity updateTaskEntity(TaskEntity taskEntity, String[] fields) throws FieldParseException {
        int titleIdx = 1;
        int descriptionIdx = 2;
        int deadlineIdx = 3;
        int typeIdx = 4;

        taskEntity.setTitle(inputParser.parseString(fields[titleIdx]));
        taskEntity.setDescription(inputParser.parseString(fields[descriptionIdx]));
        taskEntity.setDeadline(inputParser.parseDate(fields[deadlineIdx]));
        taskEntity.setType(inputParser.parseTaskType(fields[typeIdx]));

        return taskEntity;
    }
}
