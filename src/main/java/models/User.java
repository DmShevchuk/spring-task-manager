package models;

import entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель {@link User} для представления данных пользователю
 * */
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private List<Task> taskList;

    public static User toModel(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setTaskList(userEntity.getTaskEntityList().stream().map(Task::toModel).collect(Collectors.toList()));
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
