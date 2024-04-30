package com.app.mvc.controller;

import com.app.mvc.entity.Employee;
import com.app.mvc.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Value("${gender}")
    private List<String> genderList;

    @Value("${countries}")
    private List<String> countryList;
     EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(@RequestParam(required = false) boolean firstName,
                                @RequestParam(required = false) boolean lastName,
                                @RequestParam(required = false) boolean dob,
                                @RequestParam(required = false) boolean gender,
                                Model theModel) {
        // get the employees from db
        ArrayList<Employee> emp = new ArrayList<>();
        emp.add(new Employee(1, "John", "Doe", "johndoe@example.com", LocalDate.parse("1990-01-01"), "StR0ngP@ssw0rd!", "StR0ngP@ssw0rd!", "USA", "Male"));
        emp.add(new Employee(2, "Alice", "Smith", "alicesmith@example.com", LocalDate.parse("1985-05-15"), "P@ssw0rd123!", "P@ssw0rd123!", "Canada", "Female"));
        emp.add(new Employee(3, "Michael", "Johnson", "michaeljohnson@example.com", LocalDate.parse("1988-09-30"), "SecureP@ss!2024", "SecureP@ss!2024", "UK", "Male"));
        emp.add(new Employee(4, "Emily", "Brown", "emilybrown@example.com", LocalDate.parse("1993-03-20"), "P@ssw0rd!9876", "P@ssw0rd!9876", "Australia", "Female"));
        emp.add(new Employee(5, "David", "Wilson", "davidwilson@example.com", LocalDate.parse("1982-07-10"), "StrongP@ssw0rd2024", "StrongP@ssw0rd2024", "Germany", "Male"));
        emp.add(new Employee(6, "Sarah", "Martinez", "sarahmartinez@example.com", LocalDate.parse("1995-11-25"), "P@ssw0rd!Secure2024", "P@ssw0rd!Secure2024", "Mexico", "Female"));
        for (Employee e : emp) {
            employeeService.save(e);
        }

        List<Employee> theEmployees = null;
        // add to the spring model
        if (firstName) {
            theEmployees = employeeService.findAllByOrderByFirstNameAsc();
        } else if (lastName) {
            theEmployees = employeeService.findAllByOrderByLastNameAsc();
        } else if (dob) {
            theEmployees = employeeService.findAllByOrderByDobAsc();
        } else if (gender) {
            theEmployees = employeeService.findAllByOrderByGenderAsc();
        } else {
            theEmployees = employeeService.findAll();
        }
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel, HttpSession session) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        session.setAttribute("countryList", countryList);
        session.setAttribute("genderList", genderList);

        return "employees/employee-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int theId,
                                    Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee theEmployee, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/employee-form";
        }
        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";

    }
}









