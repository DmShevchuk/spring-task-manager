package ru.task_manager.commands.impl.users;

import ru.task_manager.commands.Command;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.dto.UserDTO;
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
        super("show_users", "show list of users", 0);
        this.userService = userService;
    }

    @Override
    public String execute() {
        isArgQuantityCorrect();
        List<UserEntity> userEntityList = userService.getAll();
        StringBuilder totalString = new StringBuilder();
        for (UserEntity userEntity: userEntityList) {
            totalString.append(UserDTO.toDTO(userEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
