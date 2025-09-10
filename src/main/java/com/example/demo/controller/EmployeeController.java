package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.exceptions.InvalidAgeEmployeeException;
import com.example.demo.exceptions.InvalidSalaryEmployeeException;
import com.example.demo.exceptions.InvalidUpdateEmployeeException;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(@RequestParam(required = false) String gender, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return employeeService.getEmployees(gender, page, size);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) throws InvalidAgeEmployeeException, InvalidSalaryEmployeeException {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) throws InvalidUpdateEmployeeException {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("/all")
    public void empty() {
        employeeService.clearEmployees();
    }
}
