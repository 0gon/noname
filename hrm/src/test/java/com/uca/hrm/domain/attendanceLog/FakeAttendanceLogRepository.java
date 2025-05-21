package com.uca.hrm.domain.attendanceLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uca.hrm.domain.attendanceLog.port.AttendanceLogRepository;
import com.uca.hrm.domain.employee.Employee;

public class FakeAttendanceLogRepository implements AttendanceLogRepository {

    public List<AttendanceLog> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public AttendanceLog save(AttendanceLog attendanceLog) {
        data.removeIf(e -> e.getId().equals(attendanceLog.getId()));
        data.add(attendanceLog);
        return attendanceLog;
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
