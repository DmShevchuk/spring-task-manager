package ru.task_manager.factories;

import org.springframework.stereotype.Component;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link UserEntity} из ввода пользователя
 */
@Component
public class UserFactory {

    public UserEntity getUser(String[] fields) throws FieldParseException {
        int nameIdx = 0;

        UserEntity userEntity = new UserEntity();
        InputParser inputParser = new InputParser();
        userEntity.setName(inputParser.parseString(fields[nameIdx]));
        return userEntity;
    }
}
