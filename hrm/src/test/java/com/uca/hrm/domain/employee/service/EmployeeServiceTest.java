package com.uca.hrm.domain.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.uca.hrm.comm.util.FakeIdFactory;
import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.business.EmployeeCRUDService;
import com.uca.hrm.domain.employee.repository.FakeEmployeeRepository;

public class EmployeeServiceTest {

    private EmployeeCRUDService employeeService;

    @BeforeEach
    void setUp() {
        Clock clock = Clock.fixed(LocalDate.of(2024, 05, 31).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault());
        FakeEmployeeRepository fakeEmployeeRepository = new FakeEmployeeRepository(clock);
        FakeIdFactory fakeIdFactory = new FakeIdFactory();
        employeeService = new EmployeeCRUDService(fakeEmployeeRepository, fakeIdFactory);
    }

    @Test
    void testAddEmployee() {
        // given
        Employee employee = employeeService.addEmployee(
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.parse("2023-01-01"),
                "Engineering",
                "Software Engineer",
                new BigDecimal(85000));

        // when

        // then
        assertNotNull(employee.getId());
        assertNotNull(employee);
    }

    @Test
    void testUpdateEmployee() {
        // given
        Employee employee = employeeService.addEmployee(
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.parse("2023-01-01"),
                "Engineering",
                "Software Engineer",
                new BigDecimal(85000));

        // when
        String updateFirstName = "Jane";
        String updateLastName = "Smith";

        employeeService.updateEmployee(
                employee.getId(),
                updateFirstName,
                updateLastName,
                null,
                null,
                null,
                null,
                null,
                null);

        Employee updatedEmployee = employeeService.getEmployee(employee.getId());

        // then
        assertNotEquals("John", updatedEmployee.getFirstName());
        assertNotEquals("Doe", updatedEmployee.getLastName());
        assertEquals("Jane", updatedEmployee.getFirstName());
        assertEquals("Smith", updatedEmployee.getLastName());
        assertNotNull(updatedEmployee.getId());
        assertNotNull(updatedEmployee.getEmail());
        assertNotNull(updatedEmployee.getPhoneNumber());
        assertNotNull(updatedEmployee.getHireDate());
        assertNotNull(updatedEmployee.getDepartment());
        assertNotNull(updatedEmployee.getJobTitle());
        assertNotNull(updatedEmployee.getSalary());
    }

    @Test
    void testDeactivateEmployee() {
        // given
        Employee employee = employeeService.addEmployee(
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.parse("2023-01-01"),
                "Engineering",
                "Software Engineer",
                new BigDecimal(85000));

        // when
        Employee deactivateEmployee = employeeService.deactivateEmployee(employee.getId());

        // then
        assertFalse(deactivateEmployee.isActive());
        
    }
}
