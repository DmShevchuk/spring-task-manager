package ru.task_manager.commands.impl.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task_manager.commands.Command;
import ru.task_manager.services.UserService;

/**
 * Класс, реализующий функционал удаления всех пользователей <br/>
 * При удалении всех пользователей удаляются все задачи, т.к. они связаны с пользователями <br/>
 * Если нужно избежать такого поведения - см. {@link DeleteUserById}
 */
@Component
@RequiredArgsConstructor
public class ClearUsers extends Command {
    private final UserService userService;

    @Getter
    private final String name = "clear_users";
    @Getter
    private final String info = "remove all users from collection";
    @Getter
    private final int argsQuantity = 0;

    @Override
    public String execute(String[] args) {
        isArgQuantityCorrect();
        userService.deleteAll();
        return "All users was deleted!";
    }
}
