package com.ems.employee_management_system.service.impl;
import com.ems.employee_management_system.dto.EmployeeUpdateRequest;
import com.ems.employee_management_system.entity.Employee;//import Employee entity
import com.ems.employee_management_system.repository.EmployeeRepository;//import EmployeeRepository
import com.ems.employee_management_system.service.EmployeeService;

import jakarta.validation.Valid;

import com.ems.employee_management_system.dto.EmployeeCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ems.employee_management_system.dto.EmployeeMapper;
import com.ems.employee_management_system.dto.EmployeeMapperInterface;
import com.ems.employee_management_system.dto.EmployeeResponseDTO;

@Service//--Tells spring: create an object for this class and manage it
public class EmployeeServiceImpl implements EmployeeService{
private final EmployeeRepository employeeRepository;//service depends on repository
private final EmployeeMapper mapper;//spring create objects automatically , since mapper class uses: @Component
@Autowired
private final EmployeeMapperInterface mapperInterface;
/*
 * spring Detects EmployeeRepository interface and generate runtime proxy class
 * creates an object(bean of it)
 * Injects it into EmployeeServiceImpl
 
 //we are creating this for invoking Repository and Jpa repository sql methods
 */

public EmployeeServiceImpl(EmployeeRepository employeeRepository,EmployeeMapper mapper,EmployeeMapperInterface mapperInterface) {
	this.employeeRepository = employeeRepository;
	this.mapper = mapper;
	this.mapperInterface = mapperInterface;
}

//EmployeeServiceImpl is a class where we use actuall repository methods 


//Note we implements EmployeeService , so we should define all of them which are declared in it


//--Overriding Method to create an employee
@Override
public EmployeeResponseDTO createEmployee(@Valid EmployeeCreateRequest createRequestDTO) {
	final Employee employee = mapper.toEmployeeEntity(createRequestDTO);
	return mapper.toEmployeeResponse(employeeRepository.save(employee));
	/*
	 Employee employee is actuall object of EmployeeEntity class and has values (one row with values)
	 
	 Now in service : we created create Employee method
	  
	 with Repository object, we invoke save(employee) and in sql , record is inserted into db by save method
	 
	 and return saved Entity with GeneratedId
	 */
	
	//updated logic : we create Employee entity object with mapper object and then saved into db through employeeRepository Object.save(employee)
	//and return EmployeeResponseDTO object using again mapper object, because we use dto's for showing data to Client
}

//Overriding getEmployeeById(long id)
@Override
public EmployeeResponseDTO getEmployeeById(long id){
   	return employeeRepository.findById(id)
   			.map(mapper::toEmployeeResponse)
   			.orElseThrow(()->new RuntimeException("Employee Not Found "));
   	//we are returning EmployeeResponseDTO object where we find EmployeeById that return Optional<Employee> using Employee Repository 
   	//so, using mapper we are returning EmployeeResponseDTO
   	//Note throwing RuntimeException is a bad practice use, Exception Handlers : EmployeeNotFoundException custom classes or other exceptions
}

//Overriding getAllEmployees()
@Override
public List<EmployeeResponseDTO> getAllEmployees(){
	
	//return employeeRepository.findAll().stream().map(mapper::toEmployeeResponse).collect(Collectors.toList());
	
	
	//employeeRepository.findAll() returns List<Employee>
	// .stream() method creates a stream for processing each element in List<Employee>--which means a stream is form with List<Employee> elements
	// .map(mapper::toEmployeeResponse) : map() methods apply mapper.toEmployeeResponse(each_Element_of_List<employee>)
	//which means, map() method apply the mapper.toEmployeeResponse() on all elements of the List<Employee>
	// .map() takes method reference or lambda funtion as parameter and apply them on all stream elements
	// .collect() is a terminal operation that stops the stream
	// .collect(Collectors.toList()) : Collectors.toList() takes all the stream elements or data and convert into List
	
	//so, .map() and .collect() are stream method() accessed by stream object
	
	//alternative method for  .collect(Collectors.toList())
	//.map(mapper::toEmployeeResponse).toList(); produce immutable list by default
	
	
	// .collect(Collectors.toList())--java 8+ versions only and this provides mutable list
	// .toList()--java 16+ versions only, this provides immutable list by default
	
	//we have latest version so using toList
	/**
	 @param it has no parameter
	 @return all records in List
	 
	 */
	 
	//latest java 16+ version , so using toList()
	return employeeRepository.findAll().stream().map(mapper::toEmployeeResponse).toList();
}
@Override
public EmployeeResponseDTO updateEmployee(long id,@Valid EmployeeUpdateRequest dtoRequest) {
	 final Employee existingEmployee = employeeRepository.findById(id)
			.orElseThrow(()-> new RuntimeException("ID not Found"+id));
	//employeeRepository.findById(id)--this returns Optional<Employee> optionalEmployeeObject
	
	// if employeeRepository.findById(id)-- if record found, then it returns Optional.of(employee)
	// if employeeRepository.findById(id)--if no record found in db, then it return  Optional.empty()
	
    //employeeRepository.findById(id)--if record found--return Optional.of(employee)
	//employeeRepository.findById(id)--if record not found--return Optional.empty()
	
	//Optional.empty() is a optional object  and invoke this orElseThrow() method
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
	
	 //this is commented and try modern with Mapstruct that ignores null values
	//existingEmployee.setUsername(dtoRequest.getUsername());//Update username
	//existingEmployee.setDepartment(dtoRequest.getDepartment());//Update Department
	//existingEmployee.setAddress(dtoRequest.getAddress());//Update Address
	//existingEmployee.setEmail(dtoRequest.getEmail());//Update Email
	//existingEmployee.setRole(dtoRequest.getRole());//Update Role
	 
	 mapperInterface.updateEmployeeFromDTO(dtoRequest,existingEmployee);//This is EmployeeMapperInterface obj and it has updateEmployeeFromDTO(EmployeeUpdateRequestDTO dto, @MappingTarget Employee entity);
	 //This method declared in EmployeeMapperInterface which is marked by @Mapper( componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	 //where we say's this clas is a mapper class and we are saying this class as a bean, where spring create objects for this interface
	 //and when we say @Mapper, then MapStruct creates it's Implementation as InterfaceNameImpl , and it does at compile time so no need to write it's implementation manually
	 
	 
	//so far only updated requred fields

	//Updating in DataBase and also return the Entity with Generated Db id
	
	//Using builder method to update partial
	
	return mapperInterface.toEmployeeResponse(employeeRepository.save(existingEmployee));// .save() method, if id==null insert   ,else update query
	
	
	
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
