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
    private String middleName;
    private String lastName;
    private String email;

    public static UserDTO toDTO(UserEntity userEntity) {
        UserDTO user = new UserDTO();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setMiddleName(userEntity.getMiddleName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        return user;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name=%s}<br/>", id, name);
    }
}
