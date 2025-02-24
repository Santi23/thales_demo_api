package com.company.model.dto;

import lombok.Data;

@Data
public class EmployeeThalesDTO {

    private Long id;
    private String employee_name;
    private int employee_age;
    private String profile_image;
    private Long employee_salary;
    private Long employee_annual_salary;
}
