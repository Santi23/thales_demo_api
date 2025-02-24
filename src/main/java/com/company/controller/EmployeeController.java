package com.company.controller;

import com.company.model.constant.URLConstant;
import com.company.model.dto.EmployeeThalesDTO;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = URLConstant.EMPLOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{origin}/list")
    public List<EmployeeThalesDTO> getEmployeeDTOS(@PathVariable(value = "origin") String origin){
        return employeeService.getEmployees(origin);
    }

    @GetMapping(value = "/{origin}/{id}")
    public EmployeeThalesDTO getEmployeesDTOS(@PathVariable(value = "origin") String origin,
                                              @PathVariable(value = "id") Long id){
        return employeeService.getEmployeeById(id, origin);
    }
}
