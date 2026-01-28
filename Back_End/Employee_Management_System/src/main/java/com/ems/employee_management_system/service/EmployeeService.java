package com.ems.employee_management_system.service;
//This Service an interface has only methods that EMS support no logic
//Logic is written in service Implementation

import com.ems.employee_management_system.dto.EmployeeUpdateRequest;
import com.ems.employee_management_system.entity.Employee;
//Importing employee Entity class for employee features

import java.util.List;

import java.util.Optional;//importing Optional class to handle Optional objects used in reposistory

/**
 Service layer has two things:
 1.EmployeeService-- contains employess methods or features
 2.EmployeeImplementation--contains the actually logic for employee methods or feautures
 
 Service interface for employee related bussiness operations
 
 This sits between Controller and Repository
 */

public interface EmployeeService {
//This Employee Service Interface contains only methods or features for Employee
	
	//--creating Method for Saving Employee
	Employee createEmployee(Employee employee);//This returns  saved entity with generated Id
	
	//--Creating Method to Get Employee Id
	Optional<Employee> getEmployeeById(long id);//Note: we should use only Long wrapper class to allow null
	
	//--creating Method to Get All Employees
	List<Employee> getAllEmployees();
	
	//--Creating Method to Update Employee
	Employee updateEmployee(long id, Employee employee);
	
	//--creating Method to Delete Employee By Id
	void deleteEmployee(long id);
	
	//--Creating Method to Delete Employee By Email
	void deleteEmployeeByEmail(String email);
	
	//--Creating Method to Delete Employee By Department
	void deleteEmployeeByDepartment(String department);
	
	//--Creating Method to Delete Employee By Role
	void deleteEmployeeByRole(String role);
	
	//--Creating Method to count Employee
	long countEmployeeByDepartment(String department);
	
	long deleteAllEmployees();
	
	
	
	
    
	
}
