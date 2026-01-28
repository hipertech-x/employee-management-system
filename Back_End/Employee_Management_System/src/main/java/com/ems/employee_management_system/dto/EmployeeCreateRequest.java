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
public class EmployeeCreateRequest {
	@NotNull(message="Enter joining Date")
    @PastOrPresent(message="Joining Date can't be in future")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate joiningDate;
    
    @NotBlank(message="username required")
	private String username;
    
	@NotBlank(message = "department required")
	private String department;
	@NotBlank(message="Role required")
    private String	role;
	@NotBlank(message="Email required")
	@Email(message="Enter valid email")
    private String email;
	
	
	@NotBlank(message="Phone Number required")
	@Pattern(regexp="^[0-9]{10}$")
    private String phone;
	
	@NotBlank(message="Address Required")
    private String address;
	
  @DecimalMin(value="0.01",inclusive=true,message="Salary greater than 0.00")
  @Digits(integer=10,fraction=2)
  @NotNull(message="Enter salary")
  private BigDecimal salary;
    
    
      
   
}
