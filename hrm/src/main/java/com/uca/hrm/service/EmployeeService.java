
package com.uca.hrm.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.uca.hrm.domain.Employee;
import com.uca.hrm.service.port.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    // 사원 추가
    public void addEmployee(String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate, String department, String jobTitle,
            BigDecimal salary) {

        Employee employee = Employee.create(firstName, lastName, email, phoneNumber, hireDate, department, jobTitle, salary);
        employeeRepository.save(employee);
    }

    // 사원 조회
    public Employee getEmployee(String id) {
        return employeeRepository.findById(id);
    }

    // 사원 정보 수정
    public void updateEmployee(Employee employee) {
        employeeRepository.update(employee);
    }

    // 사원 비활성화
    public void deactivateEmployee(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            employee.deativate();
            employeeRepository.update(employee);
        }
    }

}
