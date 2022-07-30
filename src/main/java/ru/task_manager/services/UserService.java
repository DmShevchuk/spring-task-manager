package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.BusiestUserNotFoundException;
import ru.task_manager.exceptions.EmailAlreadyExistsException;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.factories.TaskType;
import ru.task_manager.repositories.UserRepo;
import ru.task_manager.specification.BusiestUserSpecificationFactory;
import ru.task_manager.specification.CommonSpecificationFactory;

import java.util.Date;
import java.util.List;


/**
 * Класс для работы с базой данных и объектом класса {@link UserEntity}
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BusiestUserSpecificationFactory userSpecificationFactory;
    private final CommonSpecificationFactory specificationFactory;

    public Long registration(UserEntity userEntity) {
        if (userRepo.existsByEmail(userEntity.getEmail())) {
            throw new EmailAlreadyExistsException(userEntity.getEmail());
        }
        return userRepo.save(userEntity).getId();
    }


    public void update(UserEntity user) throws EntityNotFoundException {
        userRepo.save(user);
    }


    public Page<UserEntity> getAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }


    public UserEntity getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }


    public void delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User", id);
        }
        userRepo.deleteById(id);
    }


    public void deleteAll() {
        userRepo.deleteAll();
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

    public Page<UserEntity> getProjectUsers(ProjectEntity projectEntity, Pageable pageable) {
        return userRepo.findAll(specificationFactory.getProjectUsers(projectEntity), pageable);
    }
}
