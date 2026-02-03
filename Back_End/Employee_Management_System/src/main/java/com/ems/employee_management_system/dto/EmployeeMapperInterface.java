package com.ems.employee_management_system.dto;

import org.mapstruct.Mapper;//@Mapper marks interface as mapper
import org.mapstruct.MappingTarget;//for updating existing entity
import org.mapstruct.NullValuePropertyMappingStrategy;//for partial updates ignores null values

//importing Employee pack 
import com.ems.employee_management_system.entity.Employee;

//import packs for using DTO
//import com.ems.employee_management_system.dto.EmployeeResponseDTO;
//import com.ems.employee_management_system.dto.EmployeeCreateRequest;
//import com.ems.employee_management_system.dto.EmployeeMapper;
//import com.ems.employee_management_system.dto.EmployeeUpdateRequest;
//classes can see other files in same package: we should not import them


@Mapper(componentModel ="spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)//Marks this interface as MapStructmapper and Mapstruct automatically generates a class implements this interface (EmployeeMapperImpl)
//No need to write implementation manually, Mapstruct does it at compile time

//componentModel = "spring" --makes generated mapper a Spring bean and we can inject it into service through @Autowired or constructor injection
//nullValluePropertyMappingStrategy = NullValuePropertyMappingStratergy.IGNORE--tells MapStruct to ignore null values when mapping, perfect for partial update

public interface EmployeeMapperInterface {
public EmployeeResponseDTO toEmployeeResponse(Employee entity);
//maps employee entity to  employeeResponseDTO

public  Employee toEmployeeEntity(EmployeeCreateRequest dto);
//maps employeeCreateRequest to employee

public void updateEmployeeFromDTO(EmployeeUpdateRequest dto,@MappingTarget Employee entity);
//updates existing employee entity with values from the EmployeeUpdateRequest
//@MappingTarget--tells MapStruct "update this existing employee entity' and dont create new one
//NullValuePropertyMappingStrategy.IGNORE--ensures null fields are ignored in DTO and don't oveeride in entity vlaues

public Employee toEmployeeEntity(EmployeeResponseDTO dto);


}
