package ru.task_manager.commands.impl.users;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.services.UserService;

/**
 * Класс, реализующий функционал удаления всех пользователей <br/>
 * При удалении всех пользователей удаляются все задачи, т.к. они связаны с пользователями <br/>
 * Если нужно избежать такого поведения - см. {@link DeleteUserById}
 */
@RequiredArgsConstructor
public class ClearUsers implements Command {
    private final UserService userService;

    @Override
    public String execute() {
        userService.deleteAll();
        return "All users was deleted!";
    }
}
