package ru.task_manager.commands.impl.users;

import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import org.springframework.stereotype.Component;
import ru.task_manager.services.UserService;

/**
 * Класс, реализующий функционал удаления всех пользователей <br/>
 * При удалении всех пользователей удаляются все задачи, т.к. они связаны с пользователями <br/>
 * Если нужно избежать такого поведения - см. {@link DeleteUserById}
 * */
@Component
public class ClearUsers extends Command {
    private final UserService userService;

    public ClearUsers(UserService userService) {
        super("clear_users", "|| {-rf} remove all users from collection", 1);
        this.userService = userService;
    }

    @Override
    public String execute() {
        if (args.length > argsQuantity) {
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }
        if (args.length == 1 && "-rf".equals(args[0])) {
            userService.deleteAll();
            return "Collection of users was cleared successfully!";
        }

        return "Deleting all users will delete all tasks!<br/>" +
                "If you wish to do so, enter 'clear_users -rf'.<br/>" +
                "Otherwise, use the 'delete_user_by_id' command!";
    }
}
