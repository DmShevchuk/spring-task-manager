package ru.task_manager.dto.user;

import ru.task_manager.entities.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private Role role;

    public static UserDTO toDTO(UserEntity userEntity) {
        UserDTO user = new UserDTO();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setMiddleName(userEntity.getMiddleName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setRole(userEntity.getRole());
        return user;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name=%s}<br/>", id, name);
    }
}
