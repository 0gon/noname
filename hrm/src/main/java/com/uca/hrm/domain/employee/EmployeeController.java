package com.uca.hrm.domain.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.hrm.domain.employee.business.EmployeeCRUDService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeCRUDService employeeService;

    // 사원 추가
    @PostMapping("/employee/add")
    public ResponseEntity<Void> addEmployee(String firstName, String lastName, String email, String phoneNumber,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDate, String department, String jobTitle,
            BigDecimal salary) {
        employeeService.addEmployee(firstName, lastName, email, phoneNumber, hireDate, department, jobTitle, salary);
        return ResponseEntity.ok().build();
    }

    // 사원 조회
    @PostMapping("/employee/get")
    public ResponseEntity<Employee> getEmployee(String id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    // 사원 정보 수정
    @PostMapping("/employee/update")
    public ResponseEntity<Void> updateEmployee(String id, String firstName, String lastName, String email,
            String phoneNumber,
            LocalDate hireDate,
            String department, String jobTitle,
            BigDecimal salary) {
        employeeService.updateEmployee(id, firstName, lastName, email, phoneNumber, hireDate, department, jobTitle,
                salary);
        return ResponseEntity.ok().build();
    }

    // 사원 비활성화
    @PostMapping("/employee/deactivate")
    public ResponseEntity<Void> deactivateEmployee(String id) {
        employeeService.deactivateEmployee(id);
        return ResponseEntity.ok().build();
    }

}
