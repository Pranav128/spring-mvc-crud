package com.app.mvc.service;

import com.app.mvc.entity.Employee;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(int theId);

	void save(Employee theEmployee);

	void deleteById(int theId);

	 List<Employee> findAllByOrderByLastNameAsc();

	 List<Employee> findAllByOrderByFirstNameAsc();

	List<Employee> findAllByOrderByFirstName();

	List<Employee> findAllByOrderByDobAsc() ;

	List<Employee> findAllByOrderByGenderAsc() ;



}
