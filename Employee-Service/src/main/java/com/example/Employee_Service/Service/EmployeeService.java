package com.example.Employee_Service.Service;


import com.example.Employee_Service.Payload.APIResponseDto;
import com.example.Employee_Service.Payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
   // EmployeeDto getEmployeeById(long id);
     APIResponseDto getEmployeeById(long id);
    EmployeeDto updateEmployeeById(long id,EmployeeDto employeeDto);
    void deleteEmployee(long id);

}
