package com.ems.employee_management_system.exception;
import com.ems.employee_management_system.exception.EmployeeNotFoundException;//this is the class that has exception

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;//@ExceptionHandler(Exception_Class.class)Marks method to handle specific exceptions
import org.springframework.web.bind.annotation.RestControllerAdvice;



//@ControllerAdvice : global exception for all controllers

@RestControllerAdvice//@ControllerAdvice + @ResponseBody
//The exception handler automatically returns JSON  or plain body instead of a view 
//Perfect for Rest apis
public class GlobalExceptionHandler{
   //GlobalExceptionHandler is a custom class with no ResponseStatus, 
	//@ControllerAdvice will handle HTTP codes no need of ResponseStatus
	
	//This GlobalExceptionHandler uses the exception class and return http status code
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
