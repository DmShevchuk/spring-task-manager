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
@Component
@RequiredArgsConstructor
public class ClearTasks extends Command {
    private final TaskService taskService;
    @Getter
    private final String name = "clear_tasks";
    @Getter
    private final String info = "clear collection with tasks";
    @Getter
    private final int argsQuantity = 0;

    @Override
    public String execute(String[] args) {
        isArgQuantityCorrect();
        taskService.deleteAll();
        return "Collection of tasks was clear successfully!";
    }
}
