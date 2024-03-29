package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.entities.UserEntity;
import ru.task_manager.exceptions.EntityNotFoundException;
import ru.task_manager.repositories.ProjectRepo;
import ru.task_manager.specification.CommonSpecificationFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final UserService userService;
    private final CommonSpecificationFactory commonSpecificationFactory;


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


    public Page<ProjectEntity> getAllProjects(Pageable pageable) {
        return projectRepo.findAll(pageable);
    }


    public ProjectEntity getProjectById(Long id) {
        return projectRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Project", id));
    }


    public Page<ProjectEntity> getUserProjects(UserEntity userEntity, Pageable pageable) {
        return projectRepo.findAll(commonSpecificationFactory.getUserProject(userEntity), pageable);
    }


    public void delete(Long id) {
        if (!projectRepo.existsById(id)) {throw new EntityNotFoundException("Project", id);}
        projectRepo.deleteById(id);
    }
}
