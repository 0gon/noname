package com.uca.hrm.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.hrm.domain.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, String> {
    
}
