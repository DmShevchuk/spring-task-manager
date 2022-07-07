package ru.task_manager.commands.impl.tasks;

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

    @Override
    public String execute(String[] args) {
        isArgQuantityCorrect();
        taskService.deleteAll();
        return "Collection of tasks was clear successfully!";
    }

    @Override
    public String getName() {
        return "clear_tasks";
    }

    @Override
    public String getInfo() {
        return "clear collection with tasks";
    }

    @Override
    public int getArgsQuantity() {
        return 0;
    }
}
