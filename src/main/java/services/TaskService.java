package services;

import entities.TaskEntity;
import entities.UserEntity;
import exceptions.TaskNotFoundException;
import exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.TaskRepo;
import repositories.UserRepo;

import java.util.List;

/**
 * Класс для работы с базой данных и объектами класса {@link TaskEntity}
 * */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskEntity create(TaskEntity task, Long userId) throws UserNotFoundException{
        if(userRepo.findById(userId).isEmpty()){
            throw new UserNotFoundException(userId.toString());
        }
        UserEntity user = userRepo.findById(userId).get();
        task.setUser(user);
        return taskRepo.save(task);
    }

    public TaskEntity change(TaskEntity task) throws TaskNotFoundException{
        if(!taskRepo.existsById(task.getId())){
            throw new TaskNotFoundException(task.getId().toString());
        }

        return taskRepo.save(task);
    }

    public List<TaskEntity> getAll(){
        return taskRepo.findAll();
    }

    public Long delete(Long id) throws TaskNotFoundException{
        if (taskRepo.findById(id).isEmpty()){
            throw new TaskNotFoundException(id.toString());
        }
        taskRepo.deleteById(id);
        return id;
    }

}
