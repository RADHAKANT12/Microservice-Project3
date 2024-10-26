package com.example.Employee_Service.Payload;

import lombok.Data;

@Data
public class EmployeeDto {
    private long id;

    private String firstName;

    private String lastName;

    private String emailId;
    private String departmentCode;

}
