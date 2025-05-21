package com.uca.hrm.domain.attendanceLog.port;

import java.util.List;

import com.uca.hrm.domain.attendanceLog.AttendanceLog;
import com.uca.hrm.domain.employee.Employee;

public interface AttendanceLogRepository {

    AttendanceLog save(AttendanceLog attendanceLog);

    AttendanceLog findById(String id);

    void delete(String id);

    List<AttendanceLog> findAll();

    List<AttendanceLog> findByEmployee(Employee employee);

    List<AttendanceLog> findByEmployeeAndDate(Employee employee, String date);

}
