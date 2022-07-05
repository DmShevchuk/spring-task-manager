package users;

import entities.UserEntity;
import exceptions.FieldParseException;
import utils.InputParser;

/**
 * Класс для получения полей объекта класса {@link UserEntity} из ввода пользователя
 */
public class UserFactory {

    public UserEntity getUser(String[] fields) throws FieldParseException {
        int nameIdx = 0;

        UserEntity userEntity = new UserEntity();
        InputParser inputParser = new InputParser();
        userEntity.setName(inputParser.parseString(fields[nameIdx]));
        return userEntity;
    }
}
