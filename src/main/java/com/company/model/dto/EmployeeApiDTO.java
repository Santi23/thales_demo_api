package com.company.model.dto;

import lombok.Data;

@Data
public class EmployeeApiDTO {

    private Long id;
    private String employee_name;
    private int employee_age;
    private String profile_image;
    private Long employee_salary;

}
