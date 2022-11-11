package de.awacedmy.javadatabase.service;

import de.awacedmy.javadatabase.model.Department;
import de.awacedmy.javadatabase.model.Employee;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    void saveDepartment (Department department);
    Department getDepartmentById (long id);
    void deleteDepartmentById (long id);

//    List<Employee> getAllEmployeesByDepartmentId (long id);
}
