package com.company.controller;

import com.company.mocks.MockDTO;
import com.company.model.dto.EmployeeThalesDTO;
import com.company.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService employeeService;

    @Test
    void test_getEmployeeDTOS_ok() {
        List<EmployeeThalesDTO> employees = new ArrayList<>();
        employees.add(MockDTO.createEmployeeThalesDTO());

        when(employeeService.getEmployees(any(String.class))).thenReturn(employees);

        List<EmployeeThalesDTO> response = controller.getEmployeeDTOS("text");
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void test_getEmployeesDTOSById_ok() {
        when(employeeService.getEmployeeById(any(Long.class),any(String.class))).thenReturn(null);

        EmployeeThalesDTO response = controller.getEmployeesDTOS("text", 1L);
        assertNull(response);
    }
}