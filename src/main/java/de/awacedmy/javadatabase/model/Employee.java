package de.awacedmy.javadatabase.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name can't be empty")
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty")
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Email pattern it's not correct")
    @Column(name = "email")
    private String email;

    @Column(name="is_active", nullable = false)
    private boolean isActive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "Employee_Project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )

    Set<Project> projects = new HashSet<>();


    //default constructor
    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
