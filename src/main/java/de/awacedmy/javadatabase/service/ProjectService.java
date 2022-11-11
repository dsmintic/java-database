package de.awacedmy.javadatabase.service;

import de.awacedmy.javadatabase.model.Department;
import de.awacedmy.javadatabase.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    void saveProject (Project project);
    Project getProjectById (long id);
    void deleteProjectById (long id);
}
