package com.ems.employee_management_system.dto;

import com.ems.employee_management_system.entity.Employee;//database employee entity
import com.ems.employee_management_system.service.EmployeeService;

import java.util.Optional;

//request
import com.ems.employee_management_system.dto.EmployeeResponseDTO;//response
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
//@Data
//@AllArgsCosntructor these two are no needed, because we are not use mapper objects to build constructors for initialisation
import org.springframework.stereotype.Component;
@Component
public class EmployeeMapper {
//Now we convert EmployeeRequest object to Database..Employee entity object
	
	public  Employee toEmployeeEntity(EmployeeCreateRequest dto) {
		if(dto==null) {
			return null;
		}
		Employee employee = new Employee();
		employee.setUsername(dto.getUsername());
		employee.setDepartment(dto.getDepartment());
		employee.setEmail(dto.getEmail());
		employee.setJoiningDate(dto.getJoiningDate());
		employee.setRole(dto.getRole());
		employee.setSalary(dto.getSalary());
		employee.setPhone(dto.getPhone());
		employee.setAddress(dto.getAddress());
		return employee;
	}
		
	
	public EmployeeResponseDTO toEmployeeResponse(Employee emp) {
		if(emp == null) {
			return null;
		}
		//must match with declaration of that class fields: need to follow the declaration order in EmployeeResponseDTO class
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(emp.getId(),emp.getUsername(),emp.getEmployeeCode(),emp.getDepartment(),emp.getRole(),emp.getEmail(),emp.getPhone(),emp.getAddress(),emp.getJoiningDate(),emp.getSalary());
		
		
		//builder().field_Of_EmployeeResponseDTO(value_to_Initialise)--this is the usage of builder, but @Builder is defined in EmployeeResponseDTO
		
		//already defined or initialised through constuctor by AllArgsConstructor
		//just try to demonstrate the use of Builder().fields(value).build()
		EmployeeResponseDTO.builder().salary(emp.getSalary()).build();//This is redundant just to demonstrate
		//Note: this builder() is satatic method, so use that , but can be accessed with that class object
		//Note  the above method line of sequence return a EmployeeResponseDTO , but are not returning and also that is a anonymous object inface and only has salary field which is defined
		return employeeResponseDTO;
		
	}
	
	public  Employee toEmployeeEntity(Employee employee,EmployeeUpdateRequest dto) {
	 
	
	 //Note : we use this mapper to mapp and when convert employee to dto, in service we already check existing employeeeById
		//and use non-static functions in mapper class
		//and also use this update method as void, because we already have employee object from service and service will invoke mapper method
	 if(dto.getUsername() != null) {
	 employee.setUsername(dto.getUsername());
	 }
	 if(dto.getAddress()!=null) {
	 employee.setAddress(dto.getAddress());}
	 
	if(dto.getEmail() != null) {
	 employee.setEmail(dto.getEmail());}
	if(dto.getPhone() != null) {
	 employee.setPhone(dto.getPhone());}
	if(dto.getRole()!= null) {
	 employee.setRole(dto.getRole());}
	if(dto.getSalary() != null) {
	 employee.setSalary(dto.getSalary());}

	 return employee;
	 
		
	}
}
