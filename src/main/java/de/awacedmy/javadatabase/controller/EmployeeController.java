package de.awacedmy.javadatabase.controller;

import de.awacedmy.javadatabase.model.Department;
import de.awacedmy.javadatabase.model.Employee;
import de.awacedmy.javadatabase.model.Project;
import de.awacedmy.javadatabase.service.DepartmentService;
import de.awacedmy.javadatabase.service.EmployeeService;
import de.awacedmy.javadatabase.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private ProjectService projectService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.projectService = projectService;
    }

    @GetMapping("/employees")
    public String employeeHome(Model model) {
        model.addAttribute("listOfEmployees", employeeService.getAllEmployees());
        return "employees/index";
    }

    @GetMapping("/employees/showNewEmployeeForm")
    public String showNewEmployeeForm (Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("listOfDepartments", departmentService.getAllDepartments());
        model.addAttribute("listOfAllProjects", projectService.getAllProjects());
        return "employees/new";
    }

    @PostMapping("/employees/saveEmployee")
    public String saveEmployee (@Valid @ModelAttribute Employee employee,
                                BindingResult bidingResult,
                                Model model,
                                @RequestParam(required = false) boolean activeEmpChk){
        if(bidingResult.hasErrors()){
            model.addAttribute("listOfDepartments", departmentService.getAllDepartments());
            model.addAttribute("listOfAllProjects", projectService.getAllProjects());
            return "employees/new";
        }

        employee.setActive(activeEmpChk);

        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate (@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("listOfDepartments", departmentService.getAllDepartments());
        model.addAttribute("listOfAllProjects", projectService.getAllProjects());
        return "employees/update";
    }

    @GetMapping("/showFormForDelete/{id}")
    public String showFormForDelete (@PathVariable(value = "id") long id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

    @GetMapping("/projects")
    public String projectsHome(Model model) {
        model.addAttribute("listOfAllProjects", projectService.getAllProjects());
        return "projects/index";
    }

    @GetMapping("/projects/showNewProjectForm")
    public String showNewProjectForm (Model model){
        model.addAttribute("project", new Project());
        model.addAttribute("listOfAllProjects", projectService.getAllProjects());
        return "projects/new";
    }

    @PostMapping("/projects/saveProject")
    public String saveProject (@Valid @ModelAttribute Project project,
                                BindingResult bidingResult,
                                Model model){
        if(bidingResult.hasErrors()){
            model.addAttribute("listOfAllProjects", projectService.getAllProjects());
            return "projects/new";
        }

        projectService.saveProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/showProjectForUpdate/{id}")
    public String showProjectForUpdate (@PathVariable(value = "id") long id, Model model){
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("listOfAllProjects", projectService.getAllProjects());
        return "projects/update";
    }

    @GetMapping("/showProjectForDelete/{id}")
    public String showProjectForDelete (@PathVariable(value = "id") long id){
        projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}
