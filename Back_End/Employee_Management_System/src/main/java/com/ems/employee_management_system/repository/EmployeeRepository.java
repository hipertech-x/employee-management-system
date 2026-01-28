package com.ems.employee_management_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository is an interface that has  ready-made CRUD methods (save,find,delete)
//this JpaRepository gives all database operations to the current Repository automatically

import org.springframework.stereotype.Repository;
//it provides @Repository annotation to  mark a class or interface as a Repository bean

import com.ems.employee_management_system.entity.Employee;
//This is Employee entity class

import java.util.Optional;
//Optional is a class and it's used to assign values to object and no need to check or handle null vlaues manually
//Optional class wraps the values and no need for manual null checking

import java.util.List;
import java.math.BigDecimal;

@Repository// it's a springboot annotation marks a class or interface as repository layer component
//part of spring stereotype annotation like @Service and @Controller
public interface EmployeeRepository extends JpaRepository<Employee,Long>//JpaRepository<Entity_Class,Primary_key_data_Type>
{ //Find employee by exact email id
	Optional<Employee> findByEmail(String email);//Find all by exact email
	//Spring automatically generates queries from  method names
	//return Optional<Employee> if it's a single value 
	//return List<Employee> if it's multiple values
	
	//i.e: SELECT * FROM employee WHERE email = email
	
	//-------Finding all employees in a specific department
	List<Employee> findAllByDepartment(String department);
	
	//i.e: SELECT * FROM employee where department = department
	
	//----Finding all employees based on salary
	List<Employee> findAllBySalary(BigDecimal salary);
	//i.e SELECT * FROM employee WHERE salary = salary;
	
	//---Finding all employees based on salary Greater than specific amount
	
	List<Employee> findAllBySalaryGreaterThan(BigDecimal salary);
	//i.e SELECT * FROM employee WHERE salary > salary
	
	//----Finding all employees based on their roles
	
	List<Employee> findAllByRole(String role);
	//i.e SELECT * FROM employees WHERE role = role;
	
	//Counting employees in a specific department
	
	long countByDepartment(String department);
	
	boolean existsByDepartment(String department);//return boolean value if department exist in db
	
	long deleteByDepartment(String department);
	
	long deleteByRole(String role);
	
 //this method is already exist in JPA repository as long deleteAll()
	

}
