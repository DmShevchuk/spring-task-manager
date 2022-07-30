package ru.task_manager.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.task_manager.dto.ProjectDTO;
import ru.task_manager.dto.TaskDTO;
import ru.task_manager.dto.UserDTO;
import ru.task_manager.dto.save.UserSaveDTO;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.services.EntityRelationService;
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
    private final EntityRelationService entityRelationService;


    @PostMapping
    @ApiOperation("Добавление нового пользователя")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserSaveDTO userSaveDTO) {
        UserEntity userEntity = modelMapper.map(userSaveDTO, UserEntity.class);
        Long id = userService.registration(userEntity);
        return new ResponseEntity<>(
                        String.format("User was added successfully! Id = %d", id),
                        HttpStatus.OK);
    }


    @GetMapping
    @ApiOperation("Получение всех пользователей")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAll()
                .stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение пользователя по id")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = UserDTO.toDTO(userService.getUserById(id));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @GetMapping("/{id}/tasks")
    @ApiOperation("Получение всех задач пользователя")
    public ResponseEntity<List<TaskDTO>> getUserTasks(@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);
        List<TaskDTO> taskDTOS = entityRelationService.getUserTasks(userEntity)
                .stream()
                .map(TaskDTO::toDTO)
                .toList();
        return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}/projects")
    @ApiOperation("Получение всех проектов пользователя")
    public ResponseEntity<List<ProjectDTO>> getUserProjects(@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);
        List<ProjectDTO> projectDTOS = entityRelationService.getUserProjects(userEntity)
                .stream()
                .map(ProjectDTO::toDTO)
                .toList();
        return new ResponseEntity<>(projectDTOS, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @ApiOperation("Обновление пользователя по id")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id,
                                                  @RequestBody @Validated UserSaveDTO userSaveDTO) {
        UserEntity userEntity = modelMapper.map(userSaveDTO, UserEntity.class);
        userEntity.setId(id);
        userService.update(userEntity);
        return new ResponseEntity<>(UserDTO.toDTO(userEntity), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Удаление пользователя по id; пользователь также удаляется у задач и проектов")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        entityRelationService.removeUserFromProjects(id);
        entityRelationService.removeUserFromTasks(id);
        userService.delete(id);
        return new ResponseEntity<>("User was deleted successfully!", HttpStatus.OK);
    }
}
