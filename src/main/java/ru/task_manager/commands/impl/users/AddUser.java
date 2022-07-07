package ru.task_manager.commands.impl.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.UserService;
import ru.task_manager.factories.UserFactory;

/**
 * Класс, реализующий функционал добавления нового пользователя
 * */
@Component
@RequiredArgsConstructor
public class AddUser extends Command {
    private final UserService userService;

    @Getter
    private final String name = "add_user";
    @Getter
    private final String info = "add new user";
    @Getter
    private final int argsQuantity = 1;

    @Override
    public String execute(String[] args) throws UserAlreadyExistsException {
        isArgQuantityCorrect();
        UserEntity userEntity = new UserFactory().getUser(args);
        userService.registration(userEntity);
        resetArgs();
        return "User was added successfully!";
    }
}
