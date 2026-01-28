package com.ems.employee_management_system.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
  
	public EmployeeNotFoundException(String msg) {
		super(msg);//this invokes RuntimeException (msg)
	}//we are using this , if there is an error, it displayed by class name: EmployeeNotFoundException
	
	//the above constructor to throw onl runtime exception,but what about checked exceptions
	//for this, we need to  use throwable obj
	
	public EmployeeNotFoundException(String msg, Throwable e) {
		super(msg, e);//this invokes 
	}
	
	
}
