package com.uca.hrm.domain.attendanceLog;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uca.hrm.domain.employee.Employee;

public interface AttendanceLogRepositoryJpa extends JpaRepository<AttendanceLog, String> {

    @Query("SELECT a FROM AttendanceLog a WHERE a.employee = :employee AND a.date >= :fromDate")
    List<AttendanceLog> getAbsentsByMonth(@Param("employee") Employee employee,
            @Param("fromDate") LocalDate fromDate);

}
