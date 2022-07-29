package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.UserDTO;
import ru.task_manager.dto.save.UserSaveDTO;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> addUser(@Valid @RequestBody UserSaveDTO userSaveDTO) {
        UserEntity userEntity = modelMapper.map(userSaveDTO, UserEntity.class);
        Long id = userService.addNewUser(userEntity);
        return new ResponseEntity<>
                (
                        String.format("User was added successfully! Id = %d", id),
                        HttpStatus.OK
                );
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAll()
                .stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserEntity userEntity = userService.getUserById(id);

        return new ResponseEntity<>(UserDTO.toDTO(userEntity), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id,
                                                  @RequestBody @Validated UserSaveDTO userSaveDTO) {
        UserEntity userEntity = modelMapper.map(userSaveDTO, UserEntity.class);
        userEntity.setId(id);
        userService.updateUser(userEntity);

        return new ResponseEntity<>(UserDTO.toDTO(userEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.delete(id);

        return new ResponseEntity<>("User was deleted successfully!", HttpStatus.OK);
    }
}
