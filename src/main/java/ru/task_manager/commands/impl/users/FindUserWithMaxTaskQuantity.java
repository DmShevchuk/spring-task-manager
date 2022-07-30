package ru.task_manager.commands.impl.users;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.UserEntity;
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
        UserEntity userEntity = userService.findBusiestUser(taskType, minDate, maxDate);
        return String.format("id = %s: %s %s %s.",
                userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getEmail());
    }
}
