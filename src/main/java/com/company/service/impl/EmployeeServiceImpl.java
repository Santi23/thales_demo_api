package com.company.service.impl;

import com.company.client.EmployeesFeignClient;
import com.company.client.EmployeesRestTemplateClient;
import com.company.model.dto.EmployeeApiDTO;
import com.company.model.dto.EmployeeThalesDTO;
import com.company.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeesFeignClient feignClient;
    private final EmployeesRestTemplateClient restTemplateClient;
    private final ObjectMapper objectMapper;

    public EmployeeServiceImpl(EmployeesFeignClient feignClient, EmployeesRestTemplateClient restTemplateClient, ObjectMapper objectMapper) {
        this.feignClient = feignClient;
        this.restTemplateClient = restTemplateClient;
        this.objectMapper = objectMapper;
    }

    @Transactional
    @Override
    public List<EmployeeThalesDTO> getEmployees(String typeWebClient) {
        List<EmployeeThalesDTO> result;
        try {
            if("feign".equalsIgnoreCase(typeWebClient)){
                result = feignClient.getEmployees().getData().stream().map(this::calculateAnnualSalaryForEmployee).collect(Collectors.toList());
            }else{
                result = restTemplateClient.getEmployeesForApiRestTemplate().stream().map(this::calculateAnnualSalaryForEmployee).collect(Collectors.toList());
            }
            return result;
        } catch (Exception e) {
            System.err.println("ERROR DETECTED -> "+e.getMessage());
            return loadEmployeesFromJSON();
        }
    }

    @Override
    public EmployeeThalesDTO getEmployeeById(Long id, String typeWebClient){
        EmployeeThalesDTO result;
        if("feign".equalsIgnoreCase(typeWebClient)){
            result = calculateAnnualSalaryForEmployee(feignClient.getEmployeeById(id).getData());
        }else{
            result = calculateAnnualSalaryForEmployee(restTemplateClient.getEmployeeByIdForApiRestTemplate(id));
        }
        return result;
    }

    public EmployeeThalesDTO calculateAnnualSalaryForEmployee(EmployeeApiDTO employeeApiDTO){
        if(employeeApiDTO == null){
            return null;
        }
        EmployeeThalesDTO transformed = new EmployeeThalesDTO();
        transformed.setId(employeeApiDTO.getId());
        transformed.setEmployeeName(employeeApiDTO.getEmployee_name());
        transformed.setEmployeeSalary(employeeApiDTO.getEmployee_salary());
        transformed.setEmployeeAnnualSalary(employeeApiDTO.getEmployee_salary() * 12); // BUSINESS LOGIC REQUIRED
        transformed.setEmployeeAge(employeeApiDTO.getEmployee_age());
        return transformed;
    }

    public List<EmployeeThalesDTO> loadEmployeesFromJSON() {
        try (InputStream inputStream = getClass().getResourceAsStream("/static/employees.json")) {
            JsonNode root = objectMapper.readTree(inputStream);
            List<EmployeeApiDTO> result = objectMapper.convertValue(root.get("data"), new TypeReference<List<EmployeeApiDTO>>() {});
            return result.stream().map(this::calculateAnnualSalaryForEmployee).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error loading employees from JSON", e);
        }
    }

}
