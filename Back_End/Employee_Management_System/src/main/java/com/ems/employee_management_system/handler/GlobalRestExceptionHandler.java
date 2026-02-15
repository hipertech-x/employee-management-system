package com.ems.employee_management_system.handler;

import com.ems.employee_management_system.exception.*;//just we are importing all custom exception classes
//but not need for EmployeeNotFoundException class already handled by ResponseStatus but as a global exception handler , if that need to support to all controller need to import here


//From org.springframework.http
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

//from org.springframework.web.bind.annotation
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//other classes
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // @ControllerAdvice + @ResponseBody--> @RestControllerAdvice tells spring: 
//@ControllerAdvice: this class contains gloabl exception handling methods that can apply to all controllers
//@ResponseBody: And all this methods whatever it returns must converted to http response body (JSON instead of view)
public class GlobalRestExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)//anywhere of the controller scope, if throws this class, then this methods is invoked where we are returning the ResponseEntity
	public ResponseEntity<Map<String,Object>> handleEmployeeNotFound(EmployeeNotFoundException ex){
		Map<String,Object> error = new HashMap<>();//Hash Map is created,because json follows key-pair values but normal strings also can be returned
		error.put("Error", ex.getMessage());
		error.put("status", 404);
		error.put("timestamp", LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleEmailNotFound(EmailNotFoundException ex){
		Map<String,Object> JSON = new HashMap<>();
		JSON.put("error", ex.getMessage());
		JSON.put("status", 404);
		JSON.put("timestamp", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(JSON);
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleDepartmentNotFound(DepartmentNotFoundException ex){
		Map<String,Object>JSON = new HashMap<>();
		JSON.put("error", ex.getMessage());
		JSON.put("status", 404);
		JSON.put("timestamp", LocalDateTime.now());
		
		return new ResponseEntity<>(JSON, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleRoleNotFound(RoleNotFoundException ex){
		Map<String,Object>JSON = new HashMap<>();
		JSON.put("error", ex.getMessage());
		JSON.put("status", 404);
		JSON.put("timestamp", LocalDateTime.now());
		
		return new ResponseEntity<>(JSON, HttpStatus.NOT_FOUND);
	}
}