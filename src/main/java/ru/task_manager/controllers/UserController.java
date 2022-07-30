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
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Добавление нового пользователя")
    public Long addUser(@Valid @RequestBody UserSaveDTO userSaveDTO) {
        UserEntity userEntity = modelMapper.map(userSaveDTO, UserEntity.class);
        return userService.registration(userEntity);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех пользователей")
    public List<UserDTO> getAllUsers() {
        return userService.getAll()
                .stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение пользователя по id")
    public UserDTO getUserById(@PathVariable Long id) {
        return UserDTO.toDTO(userService.getUserById(id));
    }


    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех задач пользователя")
    public List<TaskDTO> getUserTasks(@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);
        return entityRelationService.getUserTasks(userEntity)
                .stream()
                .map(TaskDTO::toDTO)
                .toList();
    }


    @GetMapping("/{id}/projects")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение всех проектов пользователя")
    public List<ProjectDTO> getUserProjects(@PathVariable Long id){
        UserEntity userEntity = userService.getUserById(id);
        return entityRelationService.getUserProjects(userEntity)
                .stream()
                .map(ProjectDTO::toDTO)
                .toList();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Обновление пользователя по id")
    public UserDTO updateUserById(@PathVariable Long id,
                                                  @RequestBody @Validated UserSaveDTO userSaveDTO) {
        UserEntity userEntity = modelMapper.map(userSaveDTO, UserEntity.class);
        userEntity.setId(id);
        userService.update(userEntity);
        return UserDTO.toDTO(userEntity);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Удаление пользователя по id; пользователь также удаляется у задач и проектов")
    public String deleteUserById(@PathVariable Long id) {
        entityRelationService.removeUserFromProjects(id);
        entityRelationService.removeUserFromTasks(id);
        userService.delete(id);
        return "User was deleted successfully!";
    }
}
