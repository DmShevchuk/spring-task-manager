package ru.task_manager.repositories;

import ru.task_manager.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task_manager.factories.TaskType;

import javax.persistence.criteria.Predicate;
import java.util.Date;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
    boolean existsByEmail(String email);
}
