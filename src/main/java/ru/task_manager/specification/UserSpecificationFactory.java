package ru.task_manager.specification;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.TaskEntity_;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.entities.UserEntity_;
import ru.task_manager.factories.TaskType;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSpecificationFactory {
    private final SessionFactory sessionFactory;

    public Specification<UserEntity> getSpecificationForBusiestUser(TaskType taskType,
                                                                    Date minDate,
                                                                    Date maxDate){
        return (root, query, criteriaBuilder) -> {
            Session session = sessionFactory.openSession();
            CriteriaQuery<Tuple> tupleQuery = criteriaBuilder.createTupleQuery();
            Root<TaskEntity> taskRoot = tupleQuery.from(TaskEntity.class);
            Join<TaskEntity, UserEntity> taskWithUsers = taskRoot.join(TaskEntity_.user);
            Predicate predicate = createPredicateForBusiestUser(criteriaBuilder, taskRoot, taskType, minDate, maxDate);
            tupleQuery.where(predicate)
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
            List<Tuple> result = session
                    .createQuery(tupleQuery)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getResultList();

            if (result.isEmpty()){
                return criteriaBuilder.or();
            }

            Tuple resultTuple = result.get(0);
            Long userId = resultTuple.get(taskWithUsers.get(UserEntity_.id));
            return criteriaBuilder.equal(root.get(UserEntity_.id), userId);
        };

    }

    private Predicate createPredicateForBusiestUser(CriteriaBuilder criteriaBuilder,
                                                    Root<TaskEntity> taskRoot,
                                                    TaskType taskType,
                                                    Date minDate,
                                                    Date maxDate) {
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
