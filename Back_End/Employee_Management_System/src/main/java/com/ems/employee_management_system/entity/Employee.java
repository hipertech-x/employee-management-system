//this declares the package where this class belongs
package com.ems.employee_management_system.entity;

import jakarta.persistence.Entity;
//@Entity this annotation marks the class as jpa entity(maps the class to db table)

import jakarta.persistence.Table;
//@Table (name = "table_name") is declared below the @Entity annotation

import jakarta.persistence.Id;
//@Id marks the field/variable as a primary key
//declared above the variable of the entity
//@Id
//private Long id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//@Id
//@GeneratedValue (strategy = GenerationType.IDENTITY) is declared below @Id  for primary key variables, this auto generated Primary key  values using DB identity (auto-increament)
//private Long id;
// jakarta.persistence.GenerationType : Enum for generation strategies (E.g.. IDENTITY, SEQUENCE)

import jakarta.persistence.Column;
//specifies column-level constraints like nullable,unique

//@Column is declared above the Column_Varibales and ColumnName is variable name default

//Custom Column Name: @Column (name = "user_name") 
//private String username;  Column_name in DB: "username"

//Column Constraints:
//@Column( nullable = false, unique= true , length=50)
//private String columnName;
// nullable = false--column can't be null
//unique = true--- column must have unique values
//length = 50 ---set maxs length for string

//Column Definition :
//@Column( columnDefinition = "TEXT")
//private String Description;

//Precision and Scale for numbers

//@Column (precision = 10 , scale = 2)
//private BigDecimal price; BigDecimal is a java.math package for big numbers

import jakarta.validation.constraints.NotBlank;
//@NotBlank is used to validate for only strings that must not be null, ""," "

//@NotBlank
//private String username;

//Using message attribute
//@NotBlank(message = "Username can't be empty)
//private String username;

//Using groups Attributes
//@NotBlank(message = "Username required" , groups = Create.class)
//private String username;

//interface Create{};
//interface Update{};
//Validate only certain groups when needed

//there are many other validation annotaions we see like @Email and other

import jakarta.validation.constraints.Email;
//@Email --Validates string as proper Email--is declared above the Email field
//@Email  checks the string is in Email formate but doesn't check the email really exist or not 
// Eg. user@exmaple.com

//Custom Error message:
//@Email(message = "Please Enter valid email")
//private String email;

//Custom Regex Expression:
//@Email(regexp = ".*@mycompany\\.com" , message="must be company email")
//   .*  ----> any character before @
//   @mycompany\\.com  ---should be at end

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
//@Min validates number >= minimum value
//int, long, Integer, Long, BigDecimal, etc

// @Min(0)
//@Max(18)
//private int age;  age >=0

//@Min(0, message="Age must greater than  or equal to 0)
//private int age;
// i.e 0<= age <= 18


import jakarta.validation.constraints.PastOrPresent;
//@PastOrPresent --ensures a date or time field is  either in past or present day
//Prevents future dates from being entered
/* Works with :
 * java.util.Date;
 * java.time.LocalDate;
 * java.time.LocalDateTime;
 * java.time.OffsetDateTime;
 */

import java.math.BigDecimal;

//@PastOrPresent
//private LocalDate joiningDate;

//@PastOrPresent(message="Future dates can't be entered")
//private LocalDate joiningDate;

//@Past -- Date/time must be past
//@PastOrPresent -- Date/time must be Past Or Present
//@Future -- Date/time must be future
//@FutureOrPresent -- Date/time must be Future Or Present

import java.time.LocalDate;
//java class for representing date without time

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.DecimalMin;

import jakarta.validation.constraints.Pattern;

import jakarta.persistence.PrePersist;//@PrePersist--This marks a method to run automatically before a new entity is saved to database

import java.util.UUID;
@Entity  //JPA maps this employee class as table in db
@Table (name = "employee")//table Name : "employee" in db
public class Employee {
  //---------creating employee Id
    @Id  //marks the id as primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//employee id in employee table
  
  // @Id makes the "id" variable as primary
  // @GeneratedValue(strategy) is used to generate default values for id
  //@GeneratedValue(strategy=Generation.IDENTITY) --generated value and set to id based Db identity for pK
  //i.e  auto-generates PK values using DB identity  ( auto increment )
    
   //------Creating employee userName column
    @Column(nullable=false)
    @NotBlank(message="Name is mandatory")//validates that name is not null,""," "
    private String username;
    
   //-------creating employee E-mail
    @Column(nullable=false,unique=true)
    @Email(message="Invalid email format")
    @NotBlank(message = "Enter Valid Email")
    private String email;
    
    //---creating department column
    @Column(nullable=false)
    @NotBlank(message="Enter valid department")
    private String department;
    
    //---creating joining date column
    @NotNull(message="Joining date is required")
    @Column(nullable=false)
    @PastOrPresent(message="Joining date can't be Future")
    private LocalDate joiningDate;
    
    //---creating Salary Column
    @NotNull(message="Salary is required")
    @Column(nullable=false,precision=10,scale=2)
    @DecimalMin(value="0.01",inclusive=true)
    private BigDecimal salary;
    
    //---creating Role column
    @Column(nullable=false)
    @NotBlank(message="Enter Role Name")
    private String role;
    
    //---creating Phone column
    @Pattern(regexp="^[0-9]{10}$",message="phone must be 10 digits")
    @Column(name="phone")
    private String phone;
    
    //---creating Address Column
    @Column
    private String address;
    
    //----Getters and Setters for JPA Access
    
    //-----For Long id;
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    //---String username
    
    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
    
    //---String Email
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    //--String department
    public String getDepartment() {
    	return department;
    }
    public void setDepartment(String dept) {
    	this.department = dept;
    }
    
    //---LocalDate joiningDate
    public LocalDate getJoiningDate() {
    	return joiningDate;
    }
    public void setJoiningDate(LocalDate date) {
    	this.joiningDate = date;
    }
    
    //----Double salary
    public BigDecimal getSalary() {
    	return salary;
    }
    public void setSalary(BigDecimal salary) {
    	this.salary = salary;
    }
    //---String role
    public String getRole() {
    	return role;
    }
    public void setRole(String role) {
    	this.role = role;
    }
    
    //---String phone and String address
    public String getPhone() {
    	return phone;
    }
    public void setPhone(String phone){
    	this.phone = phone;
    }
    //String Address
    public String getAddress() {
    	return address;
    }
    public void setAddress(String Address) {
    	this.address = Address;
    }
    
    @Column(name="Employee_CODE",unique=true,nullable=false)
    private String employeeCode;
    
    public String getEmployeeCode() {
    	return employeeCode;
    }
    public void setEmployeeCode(String employeeCode) {
    	this.employeeCode = employeeCode;
    }
    
    //Auto Generate EmployeeCode Optional
    @PrePersist//---this marks this methods to run automatically when a new entity is saved in database
    public void generateEmployeeCode() {
    	if(this.employeeCode == null) {
    		//this.employeeCode = "EMP-"+System.currentTimeMillis();//returns current timestamp so id will be unique
    		//The above line is correct but, when multiple users use this to generatecode may not work properly and may fail to generate but it's very rare
    		
    		this.employeeCode = "EMP-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
    		
    	}
    }
    
    
}
