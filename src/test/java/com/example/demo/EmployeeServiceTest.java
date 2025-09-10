package com.example.demo;

import com.example.demo.Exception.InvalidAgeEmployeeException;
import com.example.demo.Exception.InvalidSalaryEmployeeException;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_throw_exception_when_create_a_employee() throws InvalidAgeEmployeeException, InvalidSalaryEmployeeException {
        Employee employee = new Employee(null, "Tom", 29, "MALE", 20000.0);
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        Employee employeeResult = employeeService.createEmployee(employee);

        assertEquals(employeeResult.getAge(), employee.getAge());
    }

    @Test
    void should_throw_exception_when_create_a_employee_of_age_greater_than_65_or_less_than_18() {
        Employee employee = new Employee(null, "Tom", 16, "MALE", 20000.0);
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        assertThrows(InvalidAgeEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    void should_throw_exception_when_create_a_employee_of_age_over_29_and_salary_below_20000() {
        Employee employee = new Employee(null, "Tom", 31, "MALE", 10000.0);
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        assertThrows(InvalidSalaryEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    void should_set_active_status_true_when_create_a_employee() {
        Employee employee = new Employee(null, "Tom", 18, "MALE", 10000.0);
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        assertTrue(employee.isActive());
    }
}
