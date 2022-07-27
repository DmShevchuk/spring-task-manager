package ru.task_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task_manager.entities.ProjectEntity;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {
}
