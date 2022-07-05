package ru.task_manager.commands.impl.users;

import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import ru.task_manager.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;
import ru.task_manager.services.UserService;
import ru.task_manager.utils.InputParser;

/**
 * Класс, реализующий функционал удаления пользователя по id
 * */
@Component
public class DeleteUserById extends Command {
    private final UserService userService;
    private final int TASK_ID_INDEX = 0;

    public DeleteUserById(UserService taskService) {
        super("delete_user_by_id", "delete user by id", 1);
        this.userService = taskService;
    }

    @Override
    public String execute() throws UserNotFoundException {
        isArgQuantityCorrect();
        long taskId = new InputParser().parseLong(args[TASK_ID_INDEX]);
        userService.delete(taskId);
        return String.format("User with id=%d was deleted successfully!", taskId);
    }
}
