package ru.task_manager.commands.impl.users;

import org.springframework.stereotype.Component;
import ru.task_manager.commands.Command;
import ru.task_manager.services.UserService;

/**
 * Класс, реализующий функционал удаления всех пользователей <br/>
 * При удалении всех пользователей удаляются все задачи, т.к. они связаны с пользователями <br/>
 * Если нужно избежать такого поведения - см. {@link DeleteUserById}
 */
@Component
public class ClearUsers extends Command {
    private final UserService userService;

    public ClearUsers(UserService userService) {
        super("clear_users", "remove all users from collection", 0);
        this.userService = userService;
    }

    @Override
    public String execute() {
        isArgQuantityCorrect();
        userService.deleteAll();
        return "All users was deleted!";
    }
}
