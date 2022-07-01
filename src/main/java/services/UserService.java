package services;

import entities.UserEntity;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.UserRepo;

import java.util.List;


/**
 * Класс для работы с базой данных и объектом класса {@link UserEntity}
 * */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException{
        if (userRepo.findByName(user.getName()) != null){
            throw new UserAlreadyExistsException(user.getName());
        }
        return userRepo.save(user);
    }

    public UserEntity getOne(Long id) throws UserNotFoundException{
        if (userRepo.findById(id).isEmpty()){
            throw new UserNotFoundException(id.toString());
        }
        return userRepo.findById(id).get();
    }

    public List<UserEntity> getAll(){return userRepo.findAll();}

    public Long delete(Long id) throws UserNotFoundException{
        if (userRepo.findById(id).isEmpty()){
            throw new UserNotFoundException(id.toString());
        }
        userRepo.deleteById(id);
        return id;
    }
}
