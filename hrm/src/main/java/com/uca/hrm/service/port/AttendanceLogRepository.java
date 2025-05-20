package com.uca.hrm.service.port;

import java.util.List;

import com.uca.hrm.domain.Employee;

public interface AttendanceLogRepository {

    List<Employee> getAbsentsByMonth();

}
