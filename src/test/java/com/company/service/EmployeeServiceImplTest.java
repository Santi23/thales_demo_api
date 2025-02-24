package com.company.service;

import com.company.client.EmployeesFeignClient;
import com.company.client.EmployeesRestTemplateClient;
import com.company.mocks.MockDTO;
import com.company.model.dto.EmployeeApiDTO;
import com.company.model.dto.EmployeeThalesDTO;
import com.company.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeesFeignClient feignClient;

    @Mock
    private EmployeesRestTemplateClient restTemplateClient;

    @Mock
    private ObjectMapper objectMapper;

    private EmployeeApiDTO mockApiDTO;
    private EmployeeThalesDTO expectedDTO;

    @BeforeEach
    void setUp() {
        mockApiDTO = MockDTO.createEmployeeApiDTO();
        expectedDTO = MockDTO.createEmployeeThalesDTO();
    }

    @Test
    void test_getEmployees_withFeign() {
        when(feignClient.getEmployees()).thenReturn(MockDTO.createResponseEmployeesApiDTO());

        List<EmployeeThalesDTO> result = employeeService.getEmployees("feign");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedDTO.getEmployeeAnnualSalary(), result.get(0).getEmployeeAnnualSalary());
    }

    @Test
    void test_getEmployees_withRestTemplate() {
        when(restTemplateClient.getEmployeesForApiRestTemplate()).thenReturn(Arrays.asList(mockApiDTO));

        List<EmployeeThalesDTO> result = employeeService.getEmployees("rest");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedDTO.getEmployeeAnnualSalary(), result.get(0).getEmployeeAnnualSalary());
    }

    @Test
    void test_getEmployees_withException_shouldLoadFromJSON() throws IOException {
        when(feignClient.getEmployees()).thenThrow(new RuntimeException("API Error"));

        InputStream mockInputStream = mock(InputStream.class);
        when(objectMapper.readTree(any(InputStream.class))).thenReturn(mock(JsonNode.class));
        when(objectMapper.convertValue(any(), any(TypeReference.class))).thenReturn(Arrays.asList(mockApiDTO));

        List<EmployeeThalesDTO> result = employeeService.getEmployees("feign");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void test_getEmployeeById_withFeign() {
        when(feignClient.getEmployeeById(any(Long.class))).thenReturn(MockDTO.createResponseEmployeeApiDTO());

        EmployeeThalesDTO result = employeeService.getEmployeeById(1L, "feign");

        assertNotNull(result);
        assertEquals(expectedDTO.getEmployeeAnnualSalary(), result.getEmployeeAnnualSalary());
    }

    @Test
    void test_getEmployeeById_withRestTemplate() {
        when(restTemplateClient.getEmployeeByIdForApiRestTemplate(1L)).thenReturn(mockApiDTO);

        EmployeeThalesDTO result = employeeService.getEmployeeById(1L, "rest");

        assertNotNull(result);
        assertEquals(expectedDTO.getEmployeeAnnualSalary(), result.getEmployeeAnnualSalary());
    }

    @Test
    void test_calculateAnnualSalaryForEmployee() {
        EmployeeThalesDTO result = employeeService.calculateAnnualSalaryForEmployee(mockApiDTO);

        assertNotNull(result);
        assertEquals(expectedDTO.getEmployeeAnnualSalary(), result.getEmployeeAnnualSalary());
    }

    @Test
    void test_loadEmployeesFromJSON() throws IOException {
        InputStream mockInputStream = mock(InputStream.class);
        when(objectMapper.readTree(any(InputStream.class))).thenReturn(mock(JsonNode.class));
        when(objectMapper.convertValue(any(), any(TypeReference.class))).thenReturn(Arrays.asList(mockApiDTO));

        List<EmployeeThalesDTO> result = employeeService.loadEmployeesFromJSON();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}