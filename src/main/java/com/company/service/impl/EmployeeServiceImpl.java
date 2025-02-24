package com.company.service.impl;

import com.company.client.EmployeesFeignClient;
import com.company.client.EmployeesRestTemplateClient;
import com.company.model.dto.EmployeeApiDTO;
import com.company.model.dto.EmployeeThalesDTO;
import com.company.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeesFeignClient feignClient;
    private final EmployeesRestTemplateClient restTemplateClient;

    public EmployeeServiceImpl(EmployeesFeignClient feignClient, EmployeesRestTemplateClient restTemplateClient) {
        this.feignClient = feignClient;
        this.restTemplateClient = restTemplateClient;
    }

    @Transactional
    @Override
    public List<EmployeeThalesDTO> getEmployees(String typeWebClient) {
        List<EmployeeThalesDTO> result;
        if("feign".equalsIgnoreCase(typeWebClient)){
            result = feignClient.getEmployees().getData().stream().map(this::calculateAnnualSalaryForEmployee).collect(Collectors.toList());
        }else{
            result = restTemplateClient.getEmployeesForApiRestTemplate().stream().map(this::calculateAnnualSalaryForEmployee).collect(Collectors.toList());
        }
        return result;
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

    private EmployeeThalesDTO calculateAnnualSalaryForEmployee(EmployeeApiDTO employeeApiDTO){
        if(employeeApiDTO == null){
            return null;
        }
        EmployeeThalesDTO transformed = new EmployeeThalesDTO();
        transformed.setId(employeeApiDTO.getId());
        transformed.setEmployee_name(employeeApiDTO.getEmployee_name());
        transformed.setEmployee_salary(employeeApiDTO.getEmployee_salary());
        transformed.setEmployee_annual_salary(employeeApiDTO.getEmployee_salary() * 12); // BUSINESS LOGIC REQUIRED
        transformed.setEmployee_age(employeeApiDTO.getEmployee_age());
        return transformed;
    }

}
