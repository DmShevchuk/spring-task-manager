package ru.task_manager.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.utils.InputParser;

import static org.junit.jupiter.api.Assertions.*;

class TaskFactoryTest {
    private InputParser inputParser;
    private TaskFactory taskFactory;

    @BeforeEach
    void setUp(){
        this.inputParser = new InputParser();
        this.taskFactory = new TaskFactory(inputParser);
    }

    @Test
    @DisplayName("Get TaskEntity from null")
    void getTaskEntity_WithNull() {
        String[] fields = null;

        assertThrows(NullPointerException.class, () -> taskFactory.getTaskEntity(fields));
    }

    @Test
    @DisplayName("Get TaskEntity from empty fields array")
    void getTaskEntity_WithEmptyArray() {
        String[] fields = new String[]{};

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> taskFactory.getTaskEntity(fields));
    }

    @Test
    @DisplayName("Get TaskEntity with invalid date")
    void getTaskEntity_WithInvalidDate() {
        String[] fields = new String[]{
                "Title",
                "Description",
                "String instead of date",
                "done"
        };

        assertThrows(FieldParseException.class, () -> taskFactory.getTaskEntity(fields));
    }

    @Test
    @DisplayName("Get TaskEntity with correct fields")
    void getTaskEntity_WithValidData() {
        String[] fields = new String[]{
                "Title",
                "Description",
                "11.07.2022",
                "done"
        };
        TaskEntity taskEntity = new TaskEntity.Builder()
                .setTitle(inputParser.parseString(fields[0]))
                .setDescription(inputParser.parseString(fields[1]))
                .setDeadline(inputParser.parseDate(fields[2]))
                .setTaskType(inputParser.parseTaskType(fields[3]))
                .build();
        assertEquals(taskEntity.getId(), taskFactory.getTaskEntity(fields).getId());
    }

    @Test
    @DisplayName("Update TaskEntity from null")
    void updateTaskEntity_WithNull() {
        String[] fields = null;

        assertThrows(NullPointerException.class, () -> taskFactory.updateTaskEntity(fields));
    }

    @Test
    @DisplayName("Update TaskEntity from empty fields array")
    void updateTaskEntity_WithEmptyArray() {
        String[] fields = new String[]{};

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> taskFactory.updateTaskEntity(fields));
    }


    @Test
    @DisplayName("Update TaskEntity with invalid id")
    void updateTaskEntity_WithInvalidId() {
        String[] fields = new String[]{
                "String instead of id",
                "Title",
                "Description",
                "11.07.2022",
                "done"
        };

        assertThrows(FieldParseException.class, () -> taskFactory.updateTaskEntity(fields));
    }

    @Test
    @DisplayName("Update TaskEntity with valid id")
    void updateTaskEntity_WithValidId() {
        String[] fields = new String[]{
                "1",
                "Title",
                "Description",
                "11.07.2022",
                "done"
        };
        TaskEntity taskEntity = new TaskEntity.Builder()
                .setId(inputParser.parseLong(fields[0]))
                .setTitle(inputParser.parseString(fields[1]))
                .setDescription(inputParser.parseString(fields[2]))
                .setDeadline(inputParser.parseDate(fields[3]))
                .setTaskType(inputParser.parseTaskType(fields[4]))
                .build();

        assertEquals(taskEntity.getId(), taskFactory.updateTaskEntity(fields).getId());
    }
}