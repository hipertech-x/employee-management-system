package com.ems.employee_management_system.dto;


import lombok.Getter;
import lombok.Setter;


import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Digits;
@Getter
@Setter
public class EmployeeUpdateRequest {
	
    
    
	private String username;
    
	
	private String department;
	
    private String	role;

	@Email(message="Enter valid email")
    private String email;
	
	
	@Pattern(regexp="^[0-9]{10}$", message="Phone Number must be 10 digits")
    private String phone;
	
    private String address;
	
  @DecimalMin(value="0.01",inclusive=true,message="Salary must be greater than 0.00")
  @Digits(integer=10,fraction=2)

  private BigDecimal salary;
  
  
    
    
      
   
}
