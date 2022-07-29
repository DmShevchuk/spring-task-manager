package ru.task_manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private List<Long> userIds;
    private List<Long> taskIds;

    public static ProjectDTO toDTO(ProjectEntity projectEntity) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(projectEntity.getId());
        projectDTO.setTitle(projectEntity.getTitle());
        projectDTO.setDescription(projectEntity.getDescription());
        projectDTO.setUserIds(projectEntity.getUsersEntity()
                .stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList()));
        projectDTO.setTaskIds(projectEntity.getTaskEntities()
                .stream()
                .map(TaskEntity::getId)
                .collect(Collectors.toList()));
        return projectDTO;
    }
}
