package com.ems.employee_management_system.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//Error: 404
//When we throw this class, then this ResponseStatus annotation tells spring about the error and spring directly return the http response with error: 404
public class EmployeeNotFoundException extends RuntimeException {

    // This is a custom exception class.
    // Custom exceptions are just classes that extend another exception class (like RuntimeException or Exception).

    // Why create a custom exception?
    // 1. To indicate a specific type of error clearly. 
    //    For example, "Employee not found" instead of just a generic RuntimeException.
    // 2. To make it easier to handle that specific error separately in the code.

    // Example:
    // If we have multiple arrays, a normal ArrayIndexOutOfBoundsException
    // doesn’t tell us which array caused the problem.
    // But if we create ArrayOutOfBoundsForEmployeeArray, 
    // the exception clearly indicates it’s for the employee array.

    // In short:
    // Custom exception = a named class that represents a specific problem,
    // making errors easier to understand and handle in your program.
	
	
	//Define constructors
	
	//see after creating a custom exception class it does nothing, it's just a sub-class of RuntimeException here
	
	//The actual exception work is done only by real inbuilt exception classes
	
	//1. without any parameter and throwable cause parameter
	public EmployeeNotFoundException() {
		super("Employee Not Found");//This inovkes RuntimeException("message")
	}
	
	//2. with message parameter
	public EmployeeNotFoundException(String msg) {
		super(msg);//when we call: EmployeeNotFoundException("This is error message")
	}
	
	//3.Throwable obj--to inovek RuntimeException(Throwable obj)
	public EmployeeNotFoundException(Throwable obj) {
		super(obj);
	}
	
	//4. with message paramter and Throwable obj
	public EmployeeNotFoundException(String msg, Throwable obj) {
		super(msg, obj);
	}
}
