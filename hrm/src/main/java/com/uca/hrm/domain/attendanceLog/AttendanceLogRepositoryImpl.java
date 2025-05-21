package com.uca.hrm.domain.attendanceLog;

import java.util.List;

import com.uca.hrm.domain.attendanceLog.port.AttendanceLogRepository;
import com.uca.hrm.domain.employee.Employee;

public class AttendanceLogRepositoryImpl implements AttendanceLogRepository {
    private final AttendanceLogRepositoryJpa attendanceLogRepositoryJpa;

    public AttendanceLogRepositoryImpl(AttendanceLogRepositoryJpa attendanceLogRepositoryJpa) {
        this.attendanceLogRepositoryJpa = attendanceLogRepositoryJpa;
    }

    @Override
    public AttendanceLog save(AttendanceLog attendanceLog) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public AttendanceLog findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<AttendanceLog> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<AttendanceLog> findByEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmployee'");
    }

    @Override
    public List<AttendanceLog> findByEmployeeAndDate(Employee employee, String date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmployeeAndDate'");
    }

    
}
