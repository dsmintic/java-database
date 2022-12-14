package de.awacedmy.javadatabase.service;

import de.awacedmy.javadatabase.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById (long id);
    void deleteEmployeeById (long id);
}
