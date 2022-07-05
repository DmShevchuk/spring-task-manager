package ru.task_manager.services;

import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import ru.task_manager.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.repositories.UserRepo;

import java.util.List;


/**
 * Класс для работы с базой данных и объектом класса {@link UserEntity}
 * */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void registration(UserEntity user) throws UserAlreadyExistsException{
        if (userRepo.findByName(user.getName()) != null){
            throw new UserAlreadyExistsException(user.getName());
        }
        userRepo.save(user);
    }

    public List<UserEntity> getAll(){return userRepo.findAll();}

    public void delete(Long id) throws UserNotFoundException{
        userRepo.deleteById(id);
    }

    public void deleteAll(){
        userRepo.deleteAll();
    }
}
