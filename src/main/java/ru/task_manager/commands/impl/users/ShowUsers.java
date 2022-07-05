package ru.task_manager.commands.impl.users;

import ru.task_manager.commands.Command;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.IncorrectArgsQuantityException;
import ru.task_manager.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task_manager.services.UserService;

import java.util.List;

/**
 * Класс, выводящий всех пользователей и их задач
 * */
@Component
public class ShowUsers extends Command {
    private final UserService userService;

    @Autowired
    public ShowUsers(UserService userService){
        super("show_users", "|| show list of users", 0);
        this.userService = userService;
    }

    @Override
    public String execute() {
        if(args.length != argsQuantity){
            throw new IncorrectArgsQuantityException(argsQuantity, args.length);
        }

        List<UserEntity> userEntityList = userService.getAll();
        if (userEntityList.size() == 0) {
            return "No registered users!";
        }
        StringBuilder totalString = new StringBuilder();

        for (UserEntity userEntity: userEntityList) {
            totalString.append(User.toModel(userEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
