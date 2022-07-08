package ru.task_manager.commands.impl.tasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.commands.Command;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.TaskNotFoundException;
import ru.task_manager.exceptions.UserNotFoundException;
import ru.task_manager.services.TaskService;

/**
 * Класс, реализующий функционал обновления задачи
 **/
@RequiredArgsConstructor
public class ChangeTask extends Command {
    private final TaskService taskService;
    private final TaskEntity taskEntity;
    private final Long ownerId;

    @Override
    public String execute() throws TaskNotFoundException, UserNotFoundException {
        return TaskDTO.toDTO(taskService.update(taskEntity, ownerId)).toString();
    }
}
