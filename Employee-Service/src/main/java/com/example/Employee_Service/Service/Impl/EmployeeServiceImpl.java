package com.example.Employee_Service.Service.Impl;


import com.example.Employee_Service.Entity.Employee;
import com.example.Employee_Service.Exception.ResourceNotFoundException;
import com.example.Employee_Service.Payload.APIResponseDto;
import com.example.Employee_Service.Payload.DepartmentDto;
import com.example.Employee_Service.Payload.EmployeeDto;
import com.example.Employee_Service.Repository.EmployeeRepository;
import com.example.Employee_Service.Service.ApiClient;
import com.example.Employee_Service.Service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    private EmployeeRepository employeeRepo;
    private ModelMapper mapper;
    private ApiClient apiClient;


    public EmployeeServiceImpl(EmployeeRepository employeeRepo,ModelMapper mapper,ApiClient apiClient){
        this.employeeRepo=employeeRepo;
        this.mapper=mapper;
        this.apiClient=apiClient;
    }
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        logger.info("Creating a new employee: {}", employeeDto);

        Employee employee = mapToEntity(employeeDto);
        Employee employee1 = employeeRepo.save(employee);
        EmployeeDto dto = mapToDto(employee1);
        logger.debug("Employee created successfully with id: {}", employee1.getId());

        return dto;
    }
    private Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);
        return employee;
    }
    private EmployeeDto mapToDto(Employee employee1) {
        EmployeeDto dto = mapper.map(employee1, EmployeeDto.class);
        return dto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        logger.info("Fetching all employees");

        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDto> dtos = employees.stream().map(employee -> mapToDto(employee)).collect(Collectors.toList());
        logger.debug("Number of employees fetched: {}", dtos.size());

        return dtos;
    }

    @Override
    public APIResponseDto getEmployeeById(long id) {
        logger.info("Fetching employee with id: {}", id);

        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department", "Id", id)
        );
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        EmployeeDto employeeDto = mapToDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        logger.debug("Employee with id {} fetched successfully", id);

        return apiResponseDto;
    }

    @Override
    public EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto) {
        logger.info("Updating employee with id: {}", id);

        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        Employee employee1 = employeeRepo.save(employee);
        logger.debug("Employee with id {} updated successfully", employee1.getId());

        return mapToDto(employee1);
    }

    @Override
    public void deleteEmployee(long id) {
        logger.info("Deleting employee with id: {}", id);

        Employee employee = employeeRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );
        employeeRepo.delete(employee);
        logger.debug("Employee with id {} deleted successfully", id);


    }
}
