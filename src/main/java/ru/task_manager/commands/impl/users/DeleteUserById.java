package ru.task_manager.commands.impl.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DeleteUserById extends Command {
    private final UserService userService;
    private final int TASK_ID_INDEX = 0;

    @Getter
    private final String name = "delete_user_by_id";
    @Getter
    private final String info = "delete user by id";
    @Getter
    private final int argsQuantity = 1;

    @Override
    public String execute(String[] args) throws UserNotFoundException {
        isArgQuantityCorrect();
        long taskId = new InputParser().parseLong(args[TASK_ID_INDEX]);
        userService.delete(taskId);
        return String.format("User with id=%d was deleted successfully!", taskId);
    }
}
