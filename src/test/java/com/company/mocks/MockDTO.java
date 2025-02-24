package com.company.mocks;

import com.company.model.dto.EmployeeApiDTO;
import com.company.model.dto.EmployeeThalesDTO;
import com.company.model.dto.ResponseEmployeeApiDTO;
import com.company.model.dto.ResponseEmployeesApiDTO;

import java.util.ArrayList;
import java.util.List;

public class MockDTO {

    public static EmployeeThalesDTO createEmployeeThalesDTO(){
        EmployeeThalesDTO dto = new EmployeeThalesDTO();
        dto.setId(1L);
        dto.setEmployeeAge(20);
        dto.setEmployeeSalary(5000L);
        dto.setProfileImage("");
        dto.setEmployeeName("Name");
        dto.setEmployeeAnnualSalary(dto.getEmployeeSalary() * 12);
        return dto;
    }

    public static EmployeeApiDTO createEmployeeApiDTO(){
        EmployeeApiDTO mockApiDTO = new EmployeeApiDTO();
        mockApiDTO = new EmployeeApiDTO();
        mockApiDTO.setId(1L);
        mockApiDTO.setEmployee_name("John Doe");
        mockApiDTO.setEmployee_salary(5000L);
        mockApiDTO.setEmployee_age(30);
        return mockApiDTO;
    }

    public static ResponseEmployeeApiDTO createResponseEmployeeApiDTO() {
        ResponseEmployeeApiDTO employeeApiDTO = new ResponseEmployeeApiDTO();
        employeeApiDTO.setStatus("Success");
        employeeApiDTO.setData(createEmployeeApiDTO());
        return employeeApiDTO;
    }

    public static ResponseEmployeesApiDTO createResponseEmployeesApiDTO() {
        ResponseEmployeesApiDTO employeeApiDTO = new ResponseEmployeesApiDTO();
        employeeApiDTO.setStatus("Success");
        List<EmployeeApiDTO> list = new ArrayList<>();
        list.add(createEmployeeApiDTO());
        employeeApiDTO.setData(list);
        return employeeApiDTO;
    }
}
