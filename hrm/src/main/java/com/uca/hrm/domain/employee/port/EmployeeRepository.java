package com.uca.hrm.domain.employee.port;

import java.util.List;

import com.uca.hrm.domain.employee.Employee;

public interface EmployeeRepository {
    Employee save(Employee employee);

    Employee findById(String id);

    void delete(String id);

    List<Employee> findAll();

    /**
     * 사원 연차 지급 대상자 조회
     * 
     * 1년차 이하이면 입사일 기준 다음달 당일인 사람,
     * 1년차 이상이면 입사일 기준 내년 당일인 사람 조회
     */
    List<Employee> findByLeaveRecipient();
}
