package com.uca.hrm.domain.attendanceLog;

import java.time.LocalDate;

import com.uca.hrm.comm.util.IdFactory;
import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.util.BaseField;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class AttendanceLog extends BaseField {

    @Id
    private String id; // 출결 ID
    @ManyToOne
    @JoinColumn
    private Employee employee; // 사원 ID
    private String status; // 출결 상태 (출근, 퇴근, 결근 등)
    private LocalDate date; // 날짜
    private String time; // 시간

    protected AttendanceLog() {
        super();
    }

    private AttendanceLog(String id, Employee employee, String status, LocalDate date, String time) {
        super();
        this.id = id;
        this.employee = employee;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public static AttendanceLog create(IdFactory idFactory, Employee employee, String status, LocalDate date,
            String time) {
        return new AttendanceLog(idFactory.generateAttendanceLogId(), employee, status, date, time);
    }
}
