package com.ems.employee_management_system.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.math.BigDecimal;

import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//----Builder used to initialise specific fields of a class, and we mostly used for DTO , best and powerful
public class EmployeeResponseDTO {
//This contains all employee entity field to show response based on request
	
	
	//Database Generated fields
	private Long id;
	private String username;
	private String employeeCode;
	private String department, role, email, phone, address;
	private LocalDate joiningDate;
	private BigDecimal salary;
	
}
