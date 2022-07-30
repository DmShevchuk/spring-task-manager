package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.task_manager.entities.ProjectEntity;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long>, JpaSpecificationExecutor<ProjectEntity> {
}
