package com.ems.employee_management_system.exception;

public class EmailNotFoundException extends RuntimeException{

	//we dont use @ResponseStatus, because we use global exception handlers with RestControllerAdvice
	//so we just create custom exception classes
	public EmailNotFoundException() {
		super("Email Not Found");
	}
	
	public EmailNotFoundException(String msg) {
		super(msg);
	}
	
	public EmailNotFoundException(Throwable obj) {
		super(obj);
	}
	
	public EmailNotFoundException(String msg, Throwable obj) {
		super(msg, obj);
	}
}
