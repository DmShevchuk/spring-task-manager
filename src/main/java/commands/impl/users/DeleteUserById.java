package commands.impl.users;

import commands.Command;
import exceptions.IncorrectArgsQuantityException;
import exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;
import services.UserService;
import utils.InputParser;

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
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        long taskId = new InputParser().parseLong(args[0]);
        userService.delete(taskId);
        return String.format("User with id=%d was deleted successfully!", taskId);
    }
}
