package com.company.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseEmployeesApiDTO {
    private String status;
    private List<EmployeeApiDTO> data;
}