package ru.task_manager.commands.impl.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ShowUsers extends Command {
    private final UserService userService;

    @Getter
    private final String name = "show_users";
    @Getter
    private final String info = "show list of users";
    @Getter
    private final int argsQuantity = 0;

    @Override
    public String execute(String[] args) {
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
