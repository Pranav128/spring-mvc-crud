package com.app.mvc.controller;

import java.util.List;

import com.app.mvc.service.EmployeeService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.app.mvc.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Value("${gender}")
	private List<String> genderList;

	@Value("${countries}")
	private List<String> countryList;
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(@RequestParam(required = false) boolean firstName,
								@RequestParam(required = false) boolean lastName ,
								@RequestParam(required = false)boolean dob,
								@RequestParam(required = false)boolean gender,
								Model theModel) {
		// get the employees from db
		List<Employee> theEmployees=null;
		// add to the spring model
		if (firstName) {
			theEmployees = employeeService.findAllByOrderByFirstNameAsc();
		} else if(lastName) {
			theEmployees = employeeService.findAllByOrderByLastNameAsc();
		}
		else if (dob){
			theEmployees = employeeService.findAllByOrderByDobAsc();
		}
		else if(gender){
			theEmployees = employeeService.findAllByOrderByGenderAsc();
		}
		else {
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
	public String saveEmployee(@ModelAttribute("employee")  @Valid Employee theEmployee, BindingResult result) {
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









