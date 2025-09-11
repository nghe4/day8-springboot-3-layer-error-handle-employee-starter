package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.exceptions.InvalidAgeEmployeeException;
import com.example.demo.exceptions.InvalidSalaryEmployeeException;
import com.example.demo.exceptions.InvalidUpdateEmployeeException;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private IEmployeeRepository employeeRepository;

    @Test
    void should_throw_exception_when_create_a_employee() throws InvalidSalaryEmployeeException, InvalidAgeEmployeeException {
        Employee employee = new Employee(null, "Tom", 29, "MALE", 20000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee employeeResult = employeeService.createEmployee(employee);

        assertEquals(employeeResult.getAge(), employee.getAge());
    }

    @Test
    void should_throw_exception_when_create_a_employee_of_age_greater_than_65_or_less_than_18() {
        Employee employee = new Employee(null, "Tom", 16, "MALE", 20000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertThrows(InvalidAgeEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    void should_throw_exception_when_create_a_employee_of_age_over_29_and_salary_below_20000() {
        Employee employee = new Employee(null, "Tom", 31, "MALE", 10000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertThrows(InvalidSalaryEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    void should_set_active_status_true_when_create_a_employee() {
        Employee employee = new Employee(null, "Tom", 18, "MALE", 10000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertTrue(employee.isActive());
    }

    @Test
    void should_set_active_status_false_when_delete_a_employee() {
        Employee employee = new Employee(1, "Tom", 20, "MALE", 10000.0);
        assertTrue(employee.isActive());
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1);

        verify(employeeRepository).save(argThat(e -> !e.isActive()));
    }

    @Test
    void should_throw_exception_when_update_a_employee_with_active_status_false() {
        Employee employee = new Employee(null, "Tom", 18, "MALE", 20000.0);
        employee.setActive(false);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        assertThrows(InvalidUpdateEmployeeException.class, () -> employeeService.updateEmployee(1, employee));
    }
}
