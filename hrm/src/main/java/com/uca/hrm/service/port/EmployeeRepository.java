package com.uca.hrm.service.port;

import java.util.List;

import com.uca.hrm.domain.Employee;

public interface EmployeeRepository {
    void save(Employee employee);

    Employee findById(String id);

    void update(Employee employee);

    void delete(String id);

    List<Employee> findAll();

    List<Employee> findByLeaveRecipient();
}

