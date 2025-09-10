package com.example.demo.service;

import com.example.demo.Exception.InvalidAgeEmployeeException;
import com.example.demo.Exception.InvalidSalaryEmployeeException;
import com.example.demo.Exception.InvalidUpdateEmployeeException;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        return employeeRepository.getEmployees(gender, page, size);
    }

    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        return employee;
    }

    public Employee createEmployee(Employee employee) throws InvalidAgeEmployeeException, InvalidSalaryEmployeeException {
        if (employee.getAge() == null)
            throw new InvalidAgeEmployeeException("Employee age is null");
        if (employee.getAge() < 18 || employee.getAge() > 65)
            throw new InvalidAgeEmployeeException("Employee age less than 18 or greater than 65");
        if (employee.getAge() > 29 && (employee.getSalary() == null || employee.getSalary() < 20000))
            throw new InvalidSalaryEmployeeException("Employee age over 29 and salary below 20000");
        return employeeRepository.createEmployee(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) throws InvalidUpdateEmployeeException {
        Employee employee = employeeRepository.getEmployeeById(id);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        if (!employee.isActive()) {
            throw new InvalidUpdateEmployeeException("Cannot update an inactive employee with id: " + id);
        }
        return employeeRepository.updateEmployee(id, updatedEmployee);
    }

    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        employee.setActive(false);
        employeeRepository.updateEmployee(id, employee);
    }

    public void clearEmployees() {
        employeeRepository.clearEmployees();
    }
}
