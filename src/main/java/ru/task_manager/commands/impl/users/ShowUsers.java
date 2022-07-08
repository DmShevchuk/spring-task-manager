package ru.task_manager.commands.impl.users;

import lombok.RequiredArgsConstructor;
import ru.task_manager.commands.Command;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.dto.UserDTO;

import java.util.List;

/**
 * Класс, выводящий всех пользователей и их задач
 * */
@RequiredArgsConstructor
public class ShowUsers extends Command {
    private final List<UserEntity> userEntityList;

    @Override
    public String execute() {
        StringBuilder totalString = new StringBuilder();
        for (UserEntity userEntity: userEntityList) {
            totalString.append(UserDTO.toDTO(userEntity));
            totalString.append("<br/>");
        }
        return totalString.toString();
    }
}
