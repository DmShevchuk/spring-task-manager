package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.TaskEntity_;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.entities.UserEntity_;
import ru.task_manager.exceptions.EmailAlreadyExistsException;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import ru.task_manager.exceptions.UserNotFoundException;
import ru.task_manager.factories.TaskType;
import ru.task_manager.repositories.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;


/**
 * Класс для работы с базой данных и объектом класса {@link UserEntity}
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public Long addNewUser(UserEntity userEntity) {
        if (userRepo.existsByEmail(userEntity.getEmail())) {
            throw new EmailAlreadyExistsException(userEntity.getEmail());
        }
        return userRepo.save(userEntity).getId();
    }

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
//        if (userRepo.findByName(user.getName()) != null) {
//            throw new UserAlreadyExistsException(user.getName());
//        }
        userRepo.save(user);
        return user;
    }

    public void updateUser(UserEntity user) throws UserNotFoundException {
        userRepo.save(user);
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }

    public Object[] findUserWithMaxTaskQuantity(TaskType taskType, Date minDate, Date maxDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<TaskEntity> taskRoot = query.from(TaskEntity.class);
        Join<TaskEntity, UserEntity> taskWithUsers = taskRoot.join(TaskEntity_.user);

        Predicate predicate = createPredicateForFindTasks(criteriaBuilder, taskRoot, taskType, minDate, maxDate);

        query.where(predicate)
                .multiselect(
                        taskWithUsers.get(UserEntity_.id),
                        taskWithUsers.get(UserEntity_.name),
                        criteriaBuilder.count(taskWithUsers)
                )
                .groupBy(
                        taskWithUsers.get(UserEntity_.id)
                )
                .orderBy(
                        criteriaBuilder.desc(criteriaBuilder.count(taskWithUsers))
                );
        return runQueryToFindUserWithTask(query);
    }

    private Object[] runQueryToFindUserWithTask(CriteriaQuery<Object[]> query) {
        int firstPositionOfResultToRetrieve = 0;
        int maximumNumberOfResultToRetrieve = 1;

        return entityManager
                .createQuery(query)
                .setFirstResult(firstPositionOfResultToRetrieve)
                .setMaxResults(maximumNumberOfResultToRetrieve)
                .getSingleResult();
    }

    private Predicate createPredicateForFindTasks(CriteriaBuilder criteriaBuilder,
                                                  Root<TaskEntity> taskRoot,
                                                  TaskType taskType,
                                                  Date minDate,
                                                  Date maxDate
    ) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (taskType != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(taskRoot.get(TaskEntity_.type), taskType));
        }

        if (minDate != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.greaterThanOrEqualTo(taskRoot.get(TaskEntity_.deadline), minDate));
        }

        if (maxDate != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.lessThanOrEqualTo(taskRoot.get(TaskEntity_.deadline), maxDate));
        }

        return predicate;
    }
}
