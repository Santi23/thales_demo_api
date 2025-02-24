package com.company.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseEmployeeApiDTO {
    private String status;
    private EmployeeApiDTO data;
}