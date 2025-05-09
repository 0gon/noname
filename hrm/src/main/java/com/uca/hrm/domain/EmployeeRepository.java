package com.uca.hrm.domain;

public interface EmployeeRepository {
    void save(Employee employee);

    Employee findById(String id);

    void update(Employee employee);

    void delete(String id);
}
