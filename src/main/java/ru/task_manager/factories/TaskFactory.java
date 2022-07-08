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
    private final int GET_TITLE_INDEX = 0;
    private final int GET_DESCRIPTION_INDEX = 1;
    private final int GET_DEADLINE_INDEX = 2;
    private final int GET_TYPE_INDEX = 3;

    public TaskEntity getTaskEntity(String[] fields) throws FieldParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(inputParser.parseString(fields[GET_TITLE_INDEX]));
        taskEntity.setDescription(inputParser.parseString(fields[GET_DESCRIPTION_INDEX]));
        taskEntity.setDeadline(inputParser.parseDate(fields[GET_DEADLINE_INDEX]));
        taskEntity.setType(inputParser.parseTaskType(fields[GET_TYPE_INDEX]));

        return taskEntity;
    }

    private final int UPDATE_TITLE_INDEX = 1;
    private final int UPDATE_DESCRIPTION_INDEX = 2;
    private final int UPDATE_DEADLINE_INDEX = 3;
    private final int UPDATE_TYPE_INDEX = 4;

    public TaskEntity updateTaskEntity(String[] fields) throws FieldParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(inputParser.parseLong(fields[0]));
        taskEntity.setTitle(inputParser.parseString(fields[UPDATE_TITLE_INDEX]));
        taskEntity.setDescription(inputParser.parseString(fields[UPDATE_DESCRIPTION_INDEX]));
        taskEntity.setDeadline(inputParser.parseDate(fields[UPDATE_DEADLINE_INDEX]));
        taskEntity.setType(inputParser.parseTaskType(fields[UPDATE_TYPE_INDEX]));

        return taskEntity;
    }
}
