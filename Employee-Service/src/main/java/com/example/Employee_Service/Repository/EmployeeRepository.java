package com.example.Employee_Service.Repository;

import com.example.Employee_Service.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
