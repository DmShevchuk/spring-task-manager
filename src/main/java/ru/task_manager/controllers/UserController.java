package ru.task_manager.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.UserDTO;
import ru.task_manager.dto.create.UserCreateDTO;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<String> addUser(@Valid @RequestBody UserCreateDTO userDTO){
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        Long id = userService.addNewUser(userEntity);
        return new ResponseEntity<>(
                String.format("User was added successfully! Id = %d", id),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserEntity> userEntityList = userService.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity userEntity: userEntityList){
            userDTOS.add(UserDTO.toDTO(userEntity));
        }
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);

        return new ResponseEntity<>(UserDTO.toDTO(userEntity), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id,
                                                 @RequestBody @Validated UserCreateDTO userCreateDTO){
        UserEntity userEntity = modelMapper.map(userCreateDTO, UserEntity.class);
        userEntity.setId(id);
        userService.updateUser(userEntity);

        return new ResponseEntity<>(UserDTO.toDTO(userEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.delete(id);

        return new ResponseEntity<>("User was deleted successfully!", HttpStatus.OK);
    }
}
