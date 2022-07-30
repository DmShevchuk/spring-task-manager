package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.repositories.ProjectRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final UserService userService;
    private final EntityRelationService entityRelationService;


    public Long create(ProjectEntity projectEntity, List<Long> usersIdList) {
        Set<UserEntity> userEntityList = new HashSet<>();
        for (Long userId : usersIdList) {
            userEntityList.add(userService.getUserById(userId));
        }
        projectEntity.setUsersEntity(userEntityList);
        return projectRepo.save(projectEntity).getId();
    }


    public ProjectEntity update(ProjectEntity projectEntity, Long id, List<Long> usersIdList) {
        Set<UserEntity> userEntityList = new HashSet<>();
        for (Long userId : usersIdList) {
            userEntityList.add(userService.getUserById(userId));
        }
        projectEntity.setUsersEntity(userEntityList);
        projectEntity.setId(id);
        return projectRepo.save(projectEntity);
    }


    public List<ProjectEntity> getAllProjects() {
        return projectRepo.findAll();
    }


    public ProjectEntity getProjectById(Long id) {
        return projectRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Project", id));

    }


    public void delete(Long id) {
        if (!projectRepo.existsById(id)) {throw new EntityNotFoundException("Project", id);}
        entityRelationService.removeProjectFromUsers(id);
        entityRelationService.removeProjectFromTasks(id);
        projectRepo.deleteById(id);
    }
}
