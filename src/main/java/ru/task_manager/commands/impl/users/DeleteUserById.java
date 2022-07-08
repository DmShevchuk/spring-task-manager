package ru.task_manager.commands.impl.users;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.exceptions.UserNotFoundException;
import ru.task_manager.services.UserService;

/**
 * Класс, реализующий функционал удаления пользователя по id
 * */
@RequiredArgsConstructor
public class DeleteUserById extends Command {
    private final UserService userService;
    private final Long id;

    @Override
    public String execute() throws UserNotFoundException {
        userService.delete(id);
        return String.format("User with id=%d was deleted successfully!", id);
    }
}
