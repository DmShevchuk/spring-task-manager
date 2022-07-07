package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.FieldParseException;
import ru.task_manager.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;
import ru.task_manager.factories.TaskFactory;
import ru.task_manager.utils.InputParser;


/**
 * Класс, реализующий функционал добавления новой задачи
 **/
@Component
@RequiredArgsConstructor
public class AddTask extends Command {
    private final TaskService taskService;
    private final TaskFactory taskFactory;
    private final int OWNER_ID_INDEX = 4;

    @Override
    public String execute(String[] args) throws FieldParseException, UserNotFoundException  {
        isArgQuantityCorrect();
        TaskEntity taskEntity = taskFactory.getTaskEntity(args);
        long ownerId = new InputParser().parseLong(args[OWNER_ID_INDEX]);
        taskService.add(taskEntity, ownerId);
        return "Task was added successfully!";
    }

    @Override
    public String getName() {
        return "add_task";
    }

    @Override
    public String getInfo() {
        return "add new task: title, description, deadline, type, user id";
    }

    @Override
    public int getArgsQuantity() {
        return 5;
    }
}
