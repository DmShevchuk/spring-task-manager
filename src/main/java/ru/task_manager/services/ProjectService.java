package ru.task_manager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task_manager.entities.ProjectEntity;
import ru.task_manager.exceptions.ProjectNotFoundException;
import ru.task_manager.repositories.ProjectRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;

    public void createProject(ProjectEntity projectEntity){
        projectRepo.save(projectEntity);
    }

    public void updateProject(ProjectEntity projectEntity){
        projectRepo.save(projectEntity);
    }

    public List<ProjectEntity> getAllProjects(){
        return projectRepo.findAll();
    }

    public ProjectEntity getProjectById(Long id){
        return projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException(id.toString()));

    }

    public void deleteById(Long id){
        projectRepo.deleteById(id);
    }

}
