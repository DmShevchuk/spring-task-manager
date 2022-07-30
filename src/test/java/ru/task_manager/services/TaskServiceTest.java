package ru.task_manager.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.factories.TaskType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Add task with invalid user")
    void addTask_WithInvalidUser() {
        Long idOfNonExistentUser = 0L;
        TaskEntity taskEntity = getTask();
        assertThrows(EntityNotFoundException.class, () -> taskService.add(taskEntity, idOfNonExistentUser));
    }

    @Test
    @DisplayName("Add task with correct user")
    void addTask_WithCorrectUser() {
        Long userId = userService.registration(getUser("John Doe"));
        TaskEntity taskEntity = taskService.add(getTask(), userId);

        assertEquals(userId, taskEntity.getUser().getId());
    }

    @Test
    @DisplayName("Update task with invalid id")
    void update_WithInvalidTaskId() {
        TaskEntity taskEntity = getTask();
        Long userId = 1L;
        taskEntity.setId(0L);
        assertThrows(EntityNotFoundException.class, () -> taskService.update(taskEntity, userId));
    }

    @Test
    @DisplayName("Update task with invalid owner")
    void update_WithInvalidOwner() {
        Long userId = userService.registration(getUser("Ivan Ivanov"));
        TaskEntity taskEntity = taskService.add(getTask(), userId);
        Long idOfNonExistentUser = 0L;
        assertThrows(EntityNotFoundException.class, () -> taskService.update(taskEntity, idOfNonExistentUser));
    }

    @Test
    @DisplayName("Update with correct task and user")
    void update_WithCorrectTaskAndUser() {
        Long userId = userService.registration(getUser("Steve Walmart"));
        TaskEntity taskEntity = taskService.add(getTask(), userId);
        assertEquals(taskEntity.getId(), taskService.update(taskEntity, userId).getId());
    }

    @Test
    void getAll() {
        Long userId = userService.registration(getUser("Nick Diamond"));
        int numberOfAdditions = 5;
        for (int i = 0; i < numberOfAdditions; i++){
            taskService.add(getTask(), userId);
        }
        assertEquals(numberOfAdditions, taskService.getAll().size());
    }

    TaskEntity getTask() {
        try {
            return new TaskEntity.Builder()
                    .setTitle("Title")
                    .setDescription("Description")
                    .setDeadline(new SimpleDateFormat("dd.MM.yyyy").parse("11.07.2022"))
                    .setTaskType(TaskType.NEW)
                    .build();
        } catch (ParseException e) {
            return null;
        }
    }

    UserEntity getUser(String name){
        return new UserEntity.Builder()
                .setName(name)
                .build();
    }
}