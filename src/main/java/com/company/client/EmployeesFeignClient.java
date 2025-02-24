package com.company.client;

import com.company.config.FeignConfig;
import com.company.model.dto.ResponseEmployeeApiDTO;
import com.company.model.dto.ResponseEmployeesApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees-client", url = "http://dummy.restapiexample.com/api/v1", configuration = FeignConfig.class)
public interface EmployeesFeignClient {
    @GetMapping("/employees")
    ResponseEmployeesApiDTO getEmployees();

    @GetMapping("/employee/{id}")
    ResponseEmployeeApiDTO getEmployeeById(@PathVariable(value = "id") Long id);
}
