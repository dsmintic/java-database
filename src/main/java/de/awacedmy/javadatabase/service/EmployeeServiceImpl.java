package de.awacedmy.javadatabase.service;

import de.awacedmy.javadatabase.model.Employee;
import de.awacedmy.javadatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(); // SELECT *
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee); //INSERT
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = this.employeeRepository.findById(id);
        Employee employee = null;

        if(optional.isPresent()){
            employee = optional.get();
        }else {
            throw  new RuntimeException("Employee with id "+ id + " was not found");
        }

        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        boolean exists = this.employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + id + " was not found.");
        }
        this.employeeRepository.deleteById(id);
    }
}
