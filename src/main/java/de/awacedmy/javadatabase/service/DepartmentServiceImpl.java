package de.awacedmy.javadatabase.service;

import de.awacedmy.javadatabase.model.Department;
import de.awacedmy.javadatabase.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        Department department = null;

        if (optional.isPresent()){
            department = optional.get();
        }else {
            throw new RuntimeException("Department with id "+ id + " was not found");
        }
        return department;
    }

    @Override
    public void deleteDepartmentById(long id) {
        boolean exists = this.departmentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Department with id " + id + " was not found.");
        }
        this.departmentRepository.deleteById(id);
    }

}
