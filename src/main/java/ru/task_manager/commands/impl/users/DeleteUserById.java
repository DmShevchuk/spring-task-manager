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

    public DeleteUserById(UserService taskService) {
        super("delete_user_by_id", "|| {id} delete user by id", 1);
        this.userService = taskService;
    }

    @Override
    public String execute() throws UserNotFoundException {
        int taskIdIdx = 0;

        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        long taskId = new InputParser().parseLong(args[taskIdIdx]);
        userService.delete(taskId);
        return String.format("User with id=%d was deleted successfully!", taskId);
    }
}
