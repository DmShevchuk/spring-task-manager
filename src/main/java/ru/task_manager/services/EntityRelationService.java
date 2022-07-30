package ru.task_manager.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EntityRelationService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void removeUserFromProjects(Long userId){
        entityManager.createNativeQuery(
                "delete from users_projects " +
                        "where id_of_user = :userId"
        ).setParameter("userId", userId).executeUpdate();
    }

    @Transactional
    public void removeUserFromTasks(Long userId){
        entityManager.createNativeQuery(
                "update tasks " +
                        "set id_of_user = null " +
                        "where id_of_user = :userId"
        ).setParameter("userId", userId).executeUpdate();
    }

    @Transactional
    public void removeProjectFromUsers(Long projectId){
        entityManager.createNativeQuery(
                "delete from users_projects " +
                        "where id_of_project = :projectId"
        ).setParameter("projectId", projectId).executeUpdate();
    }

    @Transactional
    public void removeProjectFromTasks(Long projectId){
        entityManager.createNativeQuery(
                "update tasks " +
                        "set id_of_project = null " +
                        "where id_of_project = :projectId"
        ).setParameter("projectId", projectId).executeUpdate();
    }
}
