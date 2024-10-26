package com.example.Employee_Service.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "emailId")
    private String emailId;
    private String departmentCode;


}
