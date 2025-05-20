package com.uca.hrm.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uca.hrm.domain.Employee;
import com.uca.hrm.service.port.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJpaRepository jpaEmployeeRepository;

    @Override
    public void save(Employee employee) {
        jpaEmployeeRepository.save(employee);
    }

    @Override
    public Employee findById(String id) {
        return jpaEmployeeRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Employee employee) {
        jpaEmployeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Employee> findByLeaveRecipient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLeaveRecipient'");
    }
    
}
