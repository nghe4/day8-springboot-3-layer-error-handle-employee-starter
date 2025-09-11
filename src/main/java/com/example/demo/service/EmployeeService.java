package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exceptions.InvalidAgeEmployeeException;
import com.example.demo.exceptions.InvalidSalaryEmployeeException;
import com.example.demo.exceptions.InvalidUpdateEmployeeException;
import com.example.demo.repository.IEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        if (gender == null) {
            if (page == null || size == null) {
                return employeeRepository.findAll();
            }
            else {
                Pageable pageable = PageRequest.of(page - 1, size);
                return employeeRepository.findAll(pageable).toList();
            }
        }
        else {
            if (page == null || size == null) {
                return employeeRepository.findEmployeesByGender(gender);
            }
            else {
                Pageable pageable = PageRequest.of(page - 1, size);
                return employeeRepository.findEmployeesByGender(gender, pageable).stream().toList();
            }
        }
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        return employee.get();
    }

    public Employee createEmployee(Employee employee) throws InvalidAgeEmployeeException, InvalidSalaryEmployeeException {
        if (employee.getAge() == null)
            throw new InvalidAgeEmployeeException("Employee age is null");
        if (employee.getAge() < 18 || employee.getAge() > 65)
            throw new InvalidAgeEmployeeException("Employee age less than 18 or greater than 65");
        if (employee.getSalary() == null || employee.getSalary() <= 0)
            throw new InvalidSalaryEmployeeException("Employee salary is null or less than or equal to 0");
        if (employee.getAge() > 29 && employee.getSalary() < 20000)
            throw new InvalidSalaryEmployeeException("Employee age over 29 and salary below 20000");
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) throws InvalidUpdateEmployeeException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        if (!employee.get().isActive()) {
            throw new InvalidUpdateEmployeeException("Cannot update an inactive employee with id: " + id);
        }
        updatedEmployee.setId(id);
        return employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        employeeRepository.save(employee);
    }
}
