package com.ems.employee_management_system.exception;

public class RoleNotFoundException extends RuntimeException{

	public RoleNotFoundException() {
		super("Email Not Found");
	}
	
	public RoleNotFoundException(String msg) {
		super(msg);
	}
	
	public RoleNotFoundException(Throwable obj) {
		super(obj);
	}
	
	public RoleNotFoundException(String msg, Throwable obj) {
		super(msg, obj);
	}
}
