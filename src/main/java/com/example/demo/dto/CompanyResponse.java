package com.example.demo.dto;

import com.example.demo.dto.mapper.EmployeeMapper;
import com.example.demo.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyResponse {
    private Integer id;
    private String name;
    private List<Employee> employees;

    public CompanyResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees.stream()
                .map(employee -> new EmployeeMapper().toResponse(employee))
                .collect(Collectors.toList());
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
