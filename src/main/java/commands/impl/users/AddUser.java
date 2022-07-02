package commands.impl.users;

import commands.Command;
import entities.UserEntity;
import exceptions.IncorrectArgsQuantityException;
import exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UserService;
import users.UserFactory;

/**
 * Класс, реализующий функционал добавления нового пользователя
 * */
@Component
public class AddUser extends Command {
    private final UserService userService;

    @Autowired
    public AddUser(UserService userService) {
        super("add_user", "|| add new user", 1);
        this.userService = userService;
    }

    @Override
    public String execute() throws UserAlreadyExistsException {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        UserEntity userEntity = new UserFactory().getUser(args);

        userService.registration(userEntity);
        resetArgs();
        return "User was added successfully!";
    }
}
