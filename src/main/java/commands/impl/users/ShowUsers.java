package commands.impl.users;

import commands.Command;
import entities.UserEntity;
import exceptions.IncorrectArgsQuantityException;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UserService;

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
