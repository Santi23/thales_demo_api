package com.company.service;

import com.company.model.dto.EmployeeThalesDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeThalesDTO> getEmployees(String typeWebClient);

    EmployeeThalesDTO getEmployeeById(Long id, String typeWebClient);
}
