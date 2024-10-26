package com.example.Employee_Service.Payload;


import lombok.Data;

@Data
public class APIResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;


}
