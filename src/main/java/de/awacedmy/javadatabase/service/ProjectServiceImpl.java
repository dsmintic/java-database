package de.awacedmy.javadatabase.service;

import de.awacedmy.javadatabase.model.Department;
import de.awacedmy.javadatabase.model.Project;
import de.awacedmy.javadatabase.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects (){
        return projectRepository.findAll();
    }

    public void saveProject (Project project){
        projectRepository.save(project);
    }

    @Override
    public Project getProjectById(long id) {
        Optional<Project> optional = projectRepository.findById(id);
        Project project = null;

        if (optional.isPresent()){
            project = optional.get();
        }else {
            throw new RuntimeException("Project with id "+ id + " was not found");
        }
        return project;
    }

    @Override
    public void deleteProjectById(long id) {
        boolean exists = this.projectRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Project with id " + id + " was not found.");
        }
        this.projectRepository.deleteById(id);
    }

}
