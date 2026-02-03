package com.ems.employee_management_system.controller;

//Import project packages
import com.ems.employee_management_system.entity.Employee;//Import Employee Entity
//import com.ems.employee_management_system.exception.EmployeeNotFoundException;
import com.ems.employee_management_system.service.EmployeeService;//Import Employee Service interface

//Java Utility
import java.util.List;//returning List of employees
import java.util.Optional;//returning  Optional values

// ResponseEntity class is used to wrap the employee object with Http status code and Http header information
import org.springframework.http.ResponseEntity;

//import HttpStatus which is an enum has: 
/*HttpStatus.OK            // 200
HttpStatus.CREATED       // 201
HttpStatus.NO_CONTENT    // 204
HttpStatus.NOT_FOUND     // 404
HttpStatus.BAD_REQUEST   // 400
 * these are used to create ResponseEntity object
 **/
import org.springframework.http.HttpStatus;


//importing spring Web & Rest

import org.springframework.web.bind.annotation.RestController;
//Marks the class as @RestController, that return JSON object or XML

import org.springframework.web.bind.annotation.RequestMapping;
//@RequestMapping allows to form Base url for controller : Eg: @RequestMapping("/api/employees")

import org.springframework.web.bind.annotation.GetMapping;
//@GetMapping maps Get Request to a method : Eg: @GetMapping("/{id}")

import org.springframework.web.bind.annotation.PostMapping;
//@PostMapping maps Post request to method : Eg: @PostMapping

import org.springframework.web.bind.annotation.PutMapping;
//@PutMapping maps Put request (Update) to method: Eg: @PutMapping("/{id}")

import org.springframework.web.bind.annotation.DeleteMapping;
//@DeleteMapping maps delete request to method: Eg: @DeleteMapping("/{id}")

import org.springframework.web.bind.annotation.PathVariable;
//Binds path segments from url to method parameters: Eg: /employees/{id} → @PathVariable Long id

import org.springframework.web.bind.annotation.RequestBody;
//Binds Http request JSON body  to Java Object: Eg: @RequestBody Employee employee

import org.springframework.web.bind.annotation.RequestParam;
//Binds query parameter from url to method parameter: Eg: /count?department=Sales → @RequestParam String department

/* Employee Controller handles all Employee related Http request
 * This includes creating, updating, retriving, deleting and counting employees
 * All endpoint returns Json responses with appropriate Http status code
 * **/

//import pack for dto layers
import com.ems.employee_management_system.dto.EmployeeCreateRequest;
import com.ems.employee_management_system.dto.EmployeeResponseDTO;
import com.ems.employee_management_system.dto.EmployeeMapper;
import com.ems.employee_management_system.dto.EmployeeMapperInterface;
import com.ems.employee_management_system.dto.EmployeeUpdateRequest;
//import jakarta for validation
import jakarta.validation.Valid;// @Valid triggers validation constraints , until that they can be normal variables with no validation


@RestController //This marks EmployeeController class as Controller
@RequestMapping("/api/employees")

//@RequestMapping("/api/employee") :: GET http://localhost:8080/api/employees when we hit this link by client or browser, 
// then spring filter all @RestController classes based on the url
//And spring finally understands this class will handle http request for this url and we can say any url
public class EmployeeController {
//for employeeService object, we can use : @Autowired annotation for Field injection, which means, create object by default but can't be final and not recommended
	
	//creating EmployeeService Interface object that has Services or features for employee
	private final EmployeeService employeeService;//Note This can't be null, so use constructor inject to initialise this, because this is final object
	//private final EmployeeMapper mapper = new EmployeeMapper();//this is normal object creation
	//but use @Component on EmployeeMapper class, spring automatically create objects and inject into controller
	
	private final EmployeeMapper mapper;
	
	private final EmployeeMapperInterface mapperInterface;
	//Constructor Injection
	public EmployeeController(EmployeeService employeeService,EmployeeMapper mapper,EmployeeMapperInterface mapperInterface) {
		this.employeeService = employeeService;
		this.mapper = mapper;
		this.mapperInterface = mapperInterface;
		
	}
	
	//Create Employee
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateRequest employee){
		//@PostMapping--This method triggered only on Http Post request through this @RequestMapping("/api/employee") url
		
		//@RequestBody--this annotation tells spring to convert JSON employee  Object to Java Object
		
		//ResponseEntity is a class that holds Employee object with HttpStatus code and Http Header
		
		//@Valid checks the given validation is satisfyed or not and if not , spring automatically throw a MethodArgumentNotValidException
		
		EmployeeResponseDTO savedEmployee = employeeService.createEmployee(employee);
		// createEmployee(employee) create Employee record in DB and return That Object
		
		return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
		//This return ResponseEntity<>Object that Hold EmployeeObject with given HttpStatus enum value
		//This ResponseEntity<> object is converted to JSON object by Spring AutoMatically
		
		
	}
	
//Get Employee By Id
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable long id) {
		
		EmployeeResponseDTO existingEmployee =  employeeService.getEmployeeById(id);//Note if employee is not found, RuntimeException bubbles up and spring returns  500--Internal Server Error: but it's clients error should return : 404 NotFound
		//alternative: return ResponseEntity.of(employeeService.getEmployeeById(id));   Returns 200 OK if present, 404 Not Found if empty — same behavior but less verbose.
	
		//return	new ResponseEntity<>(existingEmployee, HttpStatus.OK);
		
		//another alternative
		//return ResponseEntity.of(employeeService.getEmployeeById(id)); this works when getEmployeeById(id) returns Optional<T> type and this generate optional.empty() : 404 Error and if Optiona.of(value)--200 OK
		
	return ResponseEntity.ok(existingEmployee);// return  ResponseEntity with HttpStatus.Ok
	}
//Get All Employees
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
		List<EmployeeResponseDTO> allExistingEmployees = employeeService.getAllEmployees();
		
		return new ResponseEntity<>(allExistingEmployees,HttpStatus.OK);
	}
	

	//Update Employee By ID
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(@PathVariable long id,@Valid @RequestBody EmployeeUpdateRequest employee){
		//pass this RequestBody to Service
	//EmployeeResponseDTO existing  = employeeService.getEmployeeById(id);
        //The above line is enough, because this store the exising employee , if it's not there it return Runtime exception
		//the below try-catch is useless Now but still can be used
		//EmployeeNotFoundException is a custom exception class, with annotation: @ResponseStatus(HttpStatus.NOT_FOUND)--404 ERROR IF WE THROW THIS EXCEPTION
		
		//Big NOte: this existing should be done in service but i used it here which is wrong as per industry, controller should not transform
		
		// return ResponseEntity.ok(UpdatedEmployee)
		//if something else if failed unexpected error: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
		EmployeeResponseDTO updatedEmployee = employeeService.updateEmployee(id,employee);
		//No need to use try-catch, because we alreayd checked  and employee is present and it's valid to update
		
		return  ResponseEntity.ok(updatedEmployee);
		
	}
	//Delete Employee by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> DeleteEmployeeById(@PathVariable long id){
		//ResponseEntity<Void> here Void is Wrapper Class ,not void type--we return Java Employee Object with HttpStatus --then spring automatically converts into jSon
		try {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//Recommended :
		
		/* employeeService.deleteEmployee(id)
		 *   ResponseEntity.noContent().build();
		 *   noContnt()--this is a static method of ResponseEntity--set HttpStatus =  HttpStatus.NO_Content and return build object
		 *   And then with help of Build Object, invoke build() method, this finalise and return the ResponseEntity<Void> Object
		 *   In simple, we always use build() to return only ResponseEntity with no body only httpStatus
		 *   
		 *   ResponseEntity.ok(employee) this return ResponseEntity<Employee> object
		 *   
		 *   ResponseEntity.ok("String") this returns ResponseEntity<String> object
		 **/
	}
	@DeleteMapping
	public ResponseEntity<Long> deleteAllEmployees(){
		long count = employeeService.deleteAllEmployees();
		//ResponseEntity.noContent().build(); this is industry accepted but no information to user
		
		return  ResponseEntity.ok(count);//This set httpStatus OK and return count
	}
	
	
	
	
}