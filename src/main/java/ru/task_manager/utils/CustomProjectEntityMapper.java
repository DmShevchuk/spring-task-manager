package ru.task_manager.utils;

import org.springframework.stereotype.Component;
import ru.task_manager.dto.save.ProjectSaveDTO;
import ru.task_manager.entities.ProjectEntity;

@Component
public class CustomProjectEntityMapper {
    public ProjectEntity map(ProjectSaveDTO projectSaveDTO){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setTitle(projectSaveDTO.getTitle());
        projectEntity.setDescription(projectSaveDTO.getDescription());
        return projectEntity;
    }
}
