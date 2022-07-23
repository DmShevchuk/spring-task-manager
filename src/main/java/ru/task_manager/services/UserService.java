package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.TaskEntity_;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.entities.UserEntity_;
import ru.task_manager.exceptions.UserAlreadyExistsException;
import ru.task_manager.factories.TaskType;
import ru.task_manager.repositories.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByName(user.getName()) != null) {
            throw new UserAlreadyExistsException(user.getName());
        }
        userRepo.save(user);
        return user;
    }

    public UserEntity findUserWithMaxTaskQuantity(TaskType taskType, Date minDate, Date maxDate) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
//        Root<UserEntity> user = query.from(UserEntity.class);
//        ListJoin<UserEntity, TaskEntity> tasks = user.join(UserEntity_.taskEntityList);
//        query.select(user)
//                .where(cb.equal(tasks.get(TaskEntity_.title), "Do homework"))
//                .distinct(true);
//        TypedQuery<UserEntity> typedQuery = entityManager.createQuery(query);
//        for(UserEntity u: typedQuery.getResultList()){
//            List<TaskEntity> taskEntityList = u.getTaskEntityList();
//            for(TaskEntity taskEntity: taskEntityList){
//                System.out.println(TaskDTO.toDTO(taskEntity));
//            }
//        }
//        return null;
//        query.select(taskRoot)
//                .where(cb.equal(taskRoot.get(TaskEntity_.type), taskType));
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<TaskEntity> query = cb.createQuery(TaskEntity.class);
//        Root<TaskEntity> taskRoot = query.from(TaskEntity.class);
//
//
//        query
//                .groupBy(taskRoot.get(TaskEntity_.type))
//                .having(predicate);
//
//        System.out.println(entityManager.createQuery(query).getResultList());
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//
//        Subquery<TaskEntity> subQuery = cq.subquery(TaskEntity.class);
//        Root<TaskEntity> taskRoot = subQuery.from(TaskEntity.class);


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<TaskEntity> taskRoot = query.from(TaskEntity.class);
        Join<TaskEntity, UserEntity> taskWithUsers = taskRoot.join(TaskEntity_.user);

        Predicate predicate = criteriaBuilder.conjunction();

        if (taskType != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(taskRoot.get(TaskEntity_.type), taskType));
        }

        if (minDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(taskRoot.get(TaskEntity_.deadline), minDate));
        }

        if (maxDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(taskRoot.get(TaskEntity_.deadline), maxDate));
        }

        query.where(predicate);

        query.multiselect(taskWithUsers.get(UserEntity_.id),
                criteriaBuilder.count(taskWithUsers))
                .groupBy(taskWithUsers.get(UserEntity_.id));

        List<Object[]> lst = entityManager.createQuery(query).getResultList();
        for(Object[] object : lst){
            System.out.println(object[0] + "     " + object[1]);

        }
//
//        query.groupBy(taskRoot.get(TaskEntity_.user));
//        query.having(predicate);
//
//        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
//        List<Object[]> resultList = typedQuery.getResultList();
//
//        resultList.forEach(System.out::println);

        return null;
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }
}
