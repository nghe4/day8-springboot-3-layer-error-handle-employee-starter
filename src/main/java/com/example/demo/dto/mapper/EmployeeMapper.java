package com.example.demo.dto.mapper;

import com.example.demo.dto.EmployeeResponse;
import com.example.demo.dto.EmployeeRequest;
import org.springframework.beans.BeanUtils;
import com.example.demo.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

    public List<EmployeeResponse> toResponse(java.util.List<Employee> employees) {
        return employees.stream().map(this::toResponse).toList();
    }

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }
}
