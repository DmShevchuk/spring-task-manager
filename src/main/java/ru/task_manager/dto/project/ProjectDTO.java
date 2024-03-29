package ru.task_manager.dto.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.entities.ProjectEntity;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;

    public static ProjectDTO toDTO(ProjectEntity projectEntity) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(projectEntity.getId());
        projectDTO.setTitle(projectEntity.getTitle());
        projectDTO.setDescription(projectEntity.getDescription());
        return projectDTO;
    }
}
