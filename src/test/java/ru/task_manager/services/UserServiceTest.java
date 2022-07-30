package ru.task_manager.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.UserAlreadyExistsException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Registration with not unique name")
    void registration_WithNotUniqueName() {
        userService.registration(getUser("Not Unique Name"));
        assertThrows(UserAlreadyExistsException.class, () -> userService.registration(getUser("Not Unique Name")));
    }

    @Test
    @DisplayName("Registration with unique name")
    void registration_WithUniqueName() {
        UserEntity userEntity = getUser(getRandomUniqueUsername());
        assertDoesNotThrow(() -> userService.registration(userEntity));
    }

    @Test
    void getAll() {
        userService.deleteAll();

        int numberOfAdditions = 5;
        for(int i = 0; i < numberOfAdditions; i++){
            userService.registration(getUser(getRandomUniqueUsername()));
        }
        assertEquals(numberOfAdditions, userService.getAll().size());
    }

    UserEntity getUser(String name){
        return new UserEntity.Builder()
                .setName(name)
                .build();
    }

    String getRandomUniqueUsername(){
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}