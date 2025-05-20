
package com.uca.hrm.domain;

import org.junit.jupiter.api.Test;

import com.uca.hrm.comm.exception.InvalidException;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;

public class EmployeeTest {

    @Test
    @DisplayName("사원 생성 테스트")
    public void testEmployeeCreation() {
        Employee employee = Employee.create(
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.parse("2023-01-01"),
                "Engineering",
                "Software Engineer",
                new BigDecimal(85000));

        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertEquals("123-456-7890", employee.getPhoneNumber());
        assertEquals(LocalDate.parse("2023-01-01"), employee.getHireDate());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals("Software Engineer", employee.getJobTitle());
        assertEquals(new BigDecimal(85000), employee.getSalary());
    }

    @Test
    @DisplayName("사원 ID 생성 테스트")
    public void testEmployeeIdGeneration() {
        Employee employee = Employee.create(
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "987-654-3210",
                LocalDate.parse("2023-02-01"),
                "Marketing",
                "Marketing Manager",
                new BigDecimal(85000));

        assertNotNull(employee.getId());
    }

    @Test
    @DisplayName("사원 생성자 낫널 검증 테스트")
    public void testEmployeeEmailValidation() {
        Exception exception = assertThrows(InvalidException.class, () -> {
            Employee.create(
                    null,
                    null,
                    null,
                    null,
                    null,
                    "nullable",
                    "nullable",
                    null);
        });

    }

}
