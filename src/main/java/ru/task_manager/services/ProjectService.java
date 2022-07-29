package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.TaskEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.ProjectNotFoundException;
import ru.task_manager.exceptions.UserNotFoundException;
import ru.task_manager.repositories.ProjectRepo;
import ru.task_manager.repositories.TaskRepo;
import ru.task_manager.repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    public Long createProject(ProjectEntity projectEntity, List<Long> usersIdList) {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (Long userId : usersIdList) {
            userEntityList.add(userRepo.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId.toString())));
        }
        projectEntity.setUsersEntity(userEntityList);
        return projectRepo.save(projectEntity).getId();
    }

    public ProjectEntity updateProject(ProjectEntity projectEntity, List<Long> usersIdList) {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (Long userId : usersIdList) {
            userEntityList.add(userRepo.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId.toString())));
        }
        projectEntity.setUsersEntity(userEntityList);
        return projectRepo.save(projectEntity);
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepo.findAll();
    }

    public ProjectEntity getProjectById(Long id) {
        return projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException(id.toString()));

    }

    public void delete(Long id) {
        ProjectEntity projectEntity = getProjectById(id);
        List<UserEntity> userEntities = projectEntity.getUsersEntity();
        for(UserEntity userEntity: userEntities){
            userEntity.deleteProjectFromList(projectEntity);
            userRepo.save(userEntity);
        }

        List<TaskEntity> taskEntities = projectEntity.getTaskEntities();
        for (TaskEntity taskEntity: taskEntities){
            taskEntity.deleteProject();
            taskRepo.save(taskEntity);
        }

        projectRepo.deleteById(id);
    }

}
