package ru.task_manager.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link UserEntity} из ввода пользователя
 */
@Component
@RequiredArgsConstructor
public class UserFactory {
    private final InputParser inputParser;

    public UserEntity getUser(String[] fields) throws FieldParseException {
        return new UserEntity.Builder()
                .setName(inputParser.parseString(fields[0]))
                .build();
    }
}
