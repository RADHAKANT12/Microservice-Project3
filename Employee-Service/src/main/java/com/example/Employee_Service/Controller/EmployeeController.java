package com.example.Employee_Service.Controller;


import com.example.Employee_Service.Entity.Employee;
import com.example.Employee_Service.Exception.ResourceNotFoundException;
import com.example.Employee_Service.Payload.APIResponseDto;
import com.example.Employee_Service.Payload.EmployeeDto;
import com.example.Employee_Service.Repository.EmployeeRepository;
import com.example.Employee_Service.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin

public class EmployeeController {
    private EmployeeService employeeservice;
    private EmployeeRepository employeeRepo;

    public EmployeeController( EmployeeService employeeservice,EmployeeRepository employeeRepo){
        this.employeeservice=employeeservice;
        this.employeeRepo=employeeRepo;

    }

    // http://localhost:8080/api/employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeservice.createEmployee(employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    // http://localhost:8080/api/employee

    @GetMapping
    List<EmployeeDto> getAllEmployees(){
        List<EmployeeDto> dtos = employeeservice.getAllEmployees();
        return dtos;
    }
    // http://localhost:8080/api/employee/1

  /*  @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long id){
        EmployeeDto dto = employeeservice.getEmployeeById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }*/
  @GetMapping("/{id}")
  public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") long id){
      APIResponseDto responseDto = employeeservice.getEmployeeById(id);
      return new ResponseEntity<>(responseDto,HttpStatus.OK);
  }
    // http://localhost:8080/api/employee/1

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id,@RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeservice.updateEmployeeById(id, employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    // http://localhost:8080/api/employee/1

    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeservice.deleteEmployee(id);
        return new ResponseEntity<String>("Employee Entity Deleted!!",HttpStatus.OK);
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

        employeeRepo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
