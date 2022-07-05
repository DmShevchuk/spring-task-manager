package ru.task_manager.commands.impl.users;

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
public class AddUser extends Command {
    private final UserService userService;

    @Autowired
    public AddUser(UserService userService) {
        super("add_user", "add new user", 1);
        this.userService = userService;
    }

    @Override
    public String execute() throws UserAlreadyExistsException {
        isArgQuantityCorrect();
        UserEntity userEntity = new UserFactory().getUser(args);
        userService.registration(userEntity);
        resetArgs();
        return "User was added successfully!";
    }
}
