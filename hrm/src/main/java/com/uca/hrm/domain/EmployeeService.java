
package com.uca.hrm.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    // 사원 추가
    public void addEmployee(String firstName, String lastName, String email, String phoneNumber, String hireDate, String department, String jobTitle,
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
