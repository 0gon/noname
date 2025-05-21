package com.uca.hrm.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee, String> {
    
}
