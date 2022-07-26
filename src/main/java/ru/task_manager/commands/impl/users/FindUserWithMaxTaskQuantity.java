package ru.task_manager.commands.impl.users;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.factories.TaskType;
import ru.task_manager.services.UserService;

import java.util.Date;

@RequiredArgsConstructor
public class FindUserWithMaxTaskQuantity implements Command {
    private final UserService userService;
    private final TaskType taskType;
    private final Date minDate;
    private final Date maxDate;

    @Override
    public String execute() {
        int idIdx = 0;
        int nameIdx = 1;
        int taskQuantityIdx = 2;

        Object[] queryResult = userService.findUserWithMaxTaskQuantity(taskType, minDate, maxDate);
        return String.format("id = %s: %s. Task quantity: %s",
                queryResult[idIdx],
                queryResult[nameIdx],
                queryResult[taskQuantityIdx]);
    }
}
