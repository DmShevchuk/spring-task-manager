package ru.task_manager.commands.impl.tasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import org.springframework.stereotype.Component;
import ru.task_manager.services.TaskService;

/**
 *  Класс, реализующий функционал удаления всех задач
 * */
@RequiredArgsConstructor
public class ClearTasks extends Command {
    private final TaskService taskService;

    @Override
    public String execute() {
        taskService.deleteAll();
        return "Collection of tasks was clear successfully!";
    }
}
