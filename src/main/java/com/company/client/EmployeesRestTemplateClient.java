package com.company.client;

import com.company.model.constant.URLConstant;
import com.company.model.dto.EmployeeApiDTO;
import com.company.model.dto.ResponseEmployeeApiDTO;
import com.company.model.dto.ResponseEmployeesApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeesRestTemplateClient {

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeesRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<EmployeeApiDTO> getEmployeesForApiRestTemplate() {
        HttpEntity<String> entity = new HttpEntity<>(generateHeaders());
            ResponseEntity<ResponseEmployeesApiDTO> response = restTemplate.exchange(
                    URLConstant.API_EXTERN_EMPLOYEES,
                    HttpMethod.GET,
                    entity,
                    ResponseEmployeesApiDTO.class);
            return response.getBody().getData();
    }

    public EmployeeApiDTO getEmployeeByIdForApiRestTemplate(Long id){
        HttpEntity<String> entity = new HttpEntity<>(generateHeaders());

        try{
            ResponseEntity<ResponseEmployeeApiDTO> response = restTemplate.exchange(
                    URLConstant.API_EXTERN_EMPLOYEE_BY_ID+id,
                    HttpMethod.GET,
                    entity,
                    ResponseEmployeeApiDTO.class);
            return response.getBody().getData();
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method for local test
     */
    private HttpHeaders generateHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        headers.set("Accept", "*/*");
        headers.set("Connection", "keep-alive");
        headers.set("Cache-Control", "no-cache");
        headers.set("Cookie", "humans_21909=1");
        headers.set("upgrade-insecure-requests", "1");
        return headers;
    }
}
