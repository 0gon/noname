
package com.uca.hrm.domain.employee.business;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.hrm.comm.util.IdFactory;
import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.port.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeCRUDService {

    private final EmployeeRepository employeeRepository;
    private final IdFactory idFactory;

    // 사원 추가
    public Employee addEmployee(String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate,
            String department, String jobTitle,
            BigDecimal salary) {

        Employee employee = Employee.create(idFactory, firstName, lastName, email, phoneNumber, hireDate, department,
                jobTitle,
                salary);
        Employee saved = employeeRepository.save(employee);
        return saved;
    }

    // 사원 조회
    public Employee getEmployee(String id) {
        return employeeRepository.findById(id);
    }

    // 사원 정보 수정
    public Employee updateEmployee(String id, String firstName, String lastName, String email, String phoneNumber,
            LocalDate hireDate,
            String department, String jobTitle,
            BigDecimal salary) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }
        employee.update(firstName, lastName, email, phoneNumber, hireDate, department, jobTitle, salary);
        return employeeRepository.save(employee);
    }

    // 사원 비활성화
    public Employee deactivateEmployee(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }
        employee.deativate();
        return employeeRepository.save(employee);
    }

}
