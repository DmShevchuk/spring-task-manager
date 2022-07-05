package ru.task_manager.dto;

import ru.task_manager.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private List<TaskDTO> taskList;

    public static UserDTO toDTO(UserEntity userEntity) {
        UserDTO user = new UserDTO();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setTaskList(userEntity.getTaskEntityList().stream().map(TaskDTO::toDTO).collect(Collectors.toList()));
        return user;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("User{id=%d, name=%s}<br/>", id, name));

        if (taskList.size() > 0) {
            stringBuilder.append("Task list:<br/>");
            for (TaskDTO task : taskList) {
                stringBuilder.append(task.toString());
                stringBuilder.append("<br/>");
            }
        }
        return stringBuilder.toString();
    }
}
