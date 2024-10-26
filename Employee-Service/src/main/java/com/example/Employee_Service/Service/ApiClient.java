package com.example.Employee_Service.Service;

import com.example.Employee_Service.Payload.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://departmentms:8081" )
//@FeignClient(name = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://department" )

public interface ApiClient {
    // http://localhost:8081/api/ds/department/departmentCode/IT001

    @GetMapping("/api/ds/department/departmentCode/{departmentCode}")
    DepartmentDto getDepartmentByCode(@PathVariable("departmentCode") String departmentCode);




}
