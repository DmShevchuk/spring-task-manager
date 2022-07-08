package ru.task_manager.commands.impl.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Component;
import ru.task_manager.services.UserService;

/**
 * Класс, реализующий функционал добавления нового пользователя
 * */
@RequiredArgsConstructor
public class AddUser extends Command {
    private final UserService userService;
    private final UserEntity userEntity;

    @Override
    public String execute() throws UserAlreadyExistsException {
        userService.registration(userEntity);
        return "User was added successfully!";
    }
}
