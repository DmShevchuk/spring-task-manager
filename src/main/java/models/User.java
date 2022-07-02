package models;

import entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель {@link UserEntity} для представления данных пользователю
 */
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private List<Task> taskList;

    public static User toModel(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setTaskList(userEntity.getTaskEntityList().stream().map(Task::toModel).collect(Collectors.toList()));
        return user;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.format("User{id=%d, name=%s}<br/>", id, name));

        if (taskList.size() > 0) {
            stringBuilder.append("Task list:<br/>");
            for (Task task : taskList) {
                stringBuilder.append(task.toString());
                stringBuilder.append("<br/>");
            }
        }
        return stringBuilder.toString();
    }
}
