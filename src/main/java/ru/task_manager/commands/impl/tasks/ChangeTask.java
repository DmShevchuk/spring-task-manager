package ru.task_manager.commands.impl.tasks;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.dto.task.TaskDTO;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.services.TaskService;

/**
 * Класс, реализующий функционал обновления задачи
 **/
@RequiredArgsConstructor
public class ChangeTask implements Command {
    private final TaskService taskService;
    private final TaskEntity taskEntity;
    private final Long ownerId;

    @Override
    public String execute() throws EntityNotFoundException {
        return TaskDTO.toDTO(taskService.update(taskEntity, ownerId)).toString();
    }
}
