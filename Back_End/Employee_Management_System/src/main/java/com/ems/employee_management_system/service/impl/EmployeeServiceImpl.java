package com.ems.employee_management_system.service.impl;
import com.ems.employee_management_system.dto.EmployeeUpdateRequest;
import com.ems.employee_management_system.entity.Employee;//import Employee entity
import com.ems.employee_management_system.repository.EmployeeRepository;//import EmployeeRepository
import com.ems.employee_management_system.service.EmployeeService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//--Tells spring: create an object for this class and manage it
public class EmployeeServiceImpl implements EmployeeService{
private final EmployeeRepository employeeRepository;//service depends on repository
/*
 * spring Detects EmployeeRepository interface and generate runtime proxy class
 * creates an object(bean of it)
 * Injects it into EmployeeServiceImpl
 
 //we are creating this for invoking Repository and Jpa repository sql methods
 */

public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
	this.employeeRepository = employeeRepository;
}

//EmployeeServiceImpl is a class where we use actuall repository methods 


//Note we implements EmployeeService , so we should define all of them which are declared in it


//--Overriding Method to create an employee
@Override
public Employee createEmployee(Employee employee) {
	return employeeRepository.save(employee);
	/*
	 Employee employee is actuall object of EmployeeEntity class and has values (one row with values)
	 
	 Now in service : we created create Employee method
	  
	 with Repository object, we invoke save(employee) and in sql , record is inserted into db by save method
	 
	 and return saved Entity with GeneratedId
	 */
}

//Overriding getEmployeeById(long id)
@Override
public Optional<Employee> getEmployeeById(long id){
	return employeeRepository.findById(id);
}

//Overriding getAllEmployees()
@Override
public List<Employee> getAllEmployees(){
	return employeeRepository.findAll();
	//this returns all records in List
	/**
	 @param it has no parameter
	 @return all records in List
	 
	 */
	 
}
@Override
public Employee updateEmployee(long id, Employee employee) {
	Employee existingEmployee = employeeRepository.findById(id)
			.orElseThrow(()-> new RuntimeException("ID not Found"+id));
	//employeeRepository.findById(id)--this returns Optional<Employee> optionalEmployeeObject
	
	// if employeeRepository.findById(id)-- if record found, then it returns Optional.of(employee)
	// if employeeRepository.findById(id)--if no record found in db, then it return  Optional.empty()
	
    //employeeRepository.findById(id)--if record found--return Optional.of(employee)
	//employeeRepository.findById(id)--if record not found--return Optional.empty()
	
	//Optional.empty() this returns Optional Object  and invoke this orElseThrow() method
	// so in simple, if record found by id then: employeeRepository.Optional.of(employee).orElseThrow(()->new RuntimeException("invalid"));
	
	//Optional.of(employee) this creates Optional<Employee> object
	//and then Optional.of(employee).orElseThrow()
	//this full line is executed no matter what,
	
	// employeeRepository.findById(id)
	//record found-- invoke--- Optional.of(employee) and this is not hold null vlaue
	//record not found--invoke--Optional.empty() and this is null value contain
	
	//Then .orElseThrow(()->new Runtime("invalid")); this is executed
	
	// this --   .orElseThrow() method check-- if that value is not null-- return that value
	// if that value is null, then throw Exception or that lambda expression given to it
	
	//Now , Optional.empty() return OptionalObject and invoke-- OptionalObject.orElseThrow(()-lambda expression)--
	
	//Update details, Note: we are invoking Employee.java entity class
	existingEmployee.setUsername(employee.getUsername());//Update username
	existingEmployee.setDepartment(employee.getDepartment());//Update Department
	existingEmployee.setAddress(employee.getAddress());//Update Address
	existingEmployee.setEmail(employee.getEmail());//Update Email
	existingEmployee.setRole(employee.getRole());//Update Role
	//so far only updated requred fields

	//Updating in DataBase and also return the Entity with Generated Db id
	
	return employeeRepository.save(existingEmployee);// .save() method, if id==null insert   ,else update query
	
	
	
}
@Override
public void deleteEmployee(long id) {
	/*
	 if(employeeRepository.existsById(id)) {
	employeeRepository.deleteById(id);}
	else {
		throw new RuntimeException("Employee ID not Found :"+id);
	}
	*/
	//The above  if-else block condition is acceptable but not for large application
	//alternative using Optional
	
	Employee existingEmployee = employeeRepository.findById(id).
			orElseThrow(()->new RuntimeException("Id not found :"+id));
	
	employeeRepository.delete(existingEmployee);
	
}

@Override
public void deleteEmployeeByEmail(String email) {
	Employee existingEmployee = employeeRepository.findByEmail(email).
			orElseThrow(()->new RuntimeException("Email not found"+email));
	employeeRepository.delete(existingEmployee);
}

@Override
public void deleteEmployeeByDepartment(String department) {
/*	if(employeeRepository.existsByDepartment(department)) {
		employeeRepository.deleteByDepartmetn(department);
	}
	else {
		throw new RuntimeException("Invalid Department or Department Not Found :"+department);
	}*/
	
	//Alternate and Effiecient way
	long deleteCount = employeeRepository.deleteByDepartment(department);
	if(deleteCount==0) {
		throw new RuntimeException("Department Not found :"+department);
	}
}

@Override
public void deleteEmployeeByRole(String role) {
	long deleteCount = employeeRepository.deleteByRole(role);
	if(deleteCount ==0) {
		throw new RuntimeException("Role Not found :"+role);
	}
};

@Override
public long countEmployeeByDepartment(String department) {
	return employeeRepository.countByDepartment(department);
};

public long deleteAllEmployees() {
	long count = employeeRepository.count();
 employeeRepository.deleteAll();
 return count;
}









}
