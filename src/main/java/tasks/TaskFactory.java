package tasks;

import entities.TaskEntity;
import exceptions.FieldParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link Task} из ввода пользователя
 */
@Component
@RequiredArgsConstructor
public class TaskFactory {
    private final InputParser inputParser;

    public TaskEntity getTaskEntity(String[] fields) throws FieldParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(inputParser.parseString(fields[0]));
        taskEntity.setDescription(inputParser.parseString(fields[1]));
        taskEntity.setDeadline(inputParser.parseDate(fields[2]));
        taskEntity.setType(inputParser.parseTaskType(fields[3]));


        return taskEntity;
    }
}
