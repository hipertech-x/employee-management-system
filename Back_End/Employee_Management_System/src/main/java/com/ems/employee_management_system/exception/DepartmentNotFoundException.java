package com.ems.employee_management_system.exception;

public class DepartmentNotFoundException extends RuntimeException{

	public DepartmentNotFoundException() {
		super("Department Not Found");
	}
	
	public DepartmentNotFoundException(String msg) {
		super(msg);
	}
	
	public DepartmentNotFoundException(Throwable obj) {
		super(obj);
	}
	
	public DepartmentNotFoundException(String msg, Throwable obj) {
		super(msg, obj);
	}
}
