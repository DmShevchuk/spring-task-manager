package ru.task_manager.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.utils.InputParser;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {
    private InputParser inputParser;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        this.inputParser = new InputParser();
        this.userFactory = new UserFactory(inputParser);
    }

    @Test
    @DisplayName("Get user from null")
    void getUser_FromNull() {
        String[] fields = null;
        assertThrows(NullPointerException.class, () -> userFactory.getUser(fields));
    }

    @Test
    @DisplayName("Get user with empty array")
    void getUser_FromEmptyArray() {
        String[] fields = {};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> userFactory.getUser(fields));
    }


    @Test
    @DisplayName("Get user with empty string instead of name")
    void getUser_FromEmptyString() {
        String[] fields = {""};
        assertThrows(FieldParseException.class, () -> userFactory.getUser(fields));
    }


    @Test
    @DisplayName("Get user from correct fields")
    void getUser_FromCorrectFields() {
        String[] fields = {"John Doe"};
        UserEntity userEntity = new UserEntity.Builder()
                .setName(inputParser.parseString(fields[0]))
                .build();
        assertEquals(userEntity.getName(), userFactory.getUser(fields).getName());
    }
}

