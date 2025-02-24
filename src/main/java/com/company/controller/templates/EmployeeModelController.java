package com.company.controller.templates;

import com.company.model.dto.EmployeeThalesDTO;
import com.company.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeModelController {

    private final EmployeeService employeeService;

    public EmployeeModelController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getEmployees(@RequestParam(name = "id", required = false) Long id, Model model) {
        List<EmployeeThalesDTO> employees = employeeService.getEmployees("feign");

        if (id != null) {
            employees = employees.stream()
                    .filter(emp -> emp.getId() == id)
                    .collect(Collectors.toList());
            model.addAttribute("searchId", id);
        }

        model.addAttribute("employees", employees);
        return "employees"; // Maps to employees.html
    }

}
