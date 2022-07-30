package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.BusiestUserNotFoundException;
import ru.task_manager.exceptions.EmailAlreadyExistsException;
import ru.task_manager.exceptions.UserNotFoundException;
import ru.task_manager.factories.TaskType;
import ru.task_manager.repositories.ProjectRepo;
import ru.task_manager.repositories.TaskRepo;
import ru.task_manager.repositories.UserRepo;
import ru.task_manager.specification.UserSpecificationFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Класс для работы с базой данных и объектом класса {@link UserEntity}
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;
    private final UserSpecificationFactory userSpecificationFactory;
    private final EntityRelationService entityRelationService;

    public Long registration(UserEntity userEntity) {
        if (userRepo.existsByEmail(userEntity.getEmail())) {
            throw new EmailAlreadyExistsException(userEntity.getEmail());
        }
        return userRepo.save(userEntity).getId();
    }

    public void update(UserEntity user) throws UserNotFoundException {
        userRepo.save(user);
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public void delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException(id.toString());
        }
        entityRelationService.removeUserFromProjects(id);
        entityRelationService.removeUserFromTasks(id);
        userRepo.deleteById(id);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }

    public List<TaskEntity> getUserTasks(Long id) {
        return taskRepo.getTaskEntitiesByUserId(id);
    }

    public Set<ProjectEntity> getUserProjects(Long id) {
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userEntity.getProjects();
    }

    public UserEntity findBusiestUser(TaskType taskType, Date minDate, Date maxDate) {
        Specification<UserEntity> specification = userSpecificationFactory
                .getSpecificationForBusiestUser(taskType, minDate, maxDate);
        List<UserEntity> userEntities = userRepo.findAll(specification);
        if (!userEntities.isEmpty()) {
            return userEntities.get(0);
        }
        throw new BusiestUserNotFoundException();
    }

}
