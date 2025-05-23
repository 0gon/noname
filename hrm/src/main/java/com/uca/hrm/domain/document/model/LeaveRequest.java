package com.uca.hrm.domain.document.model;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.uca.hrm.comm.util.IdFactory;
import com.uca.hrm.domain.employee.Employee;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class LeaveRequest extends Document {

    private String leaveType; // 연차 종류
    private LocalDate startDate; // 연차 시작일
    private LocalDate endDate; // 연차 종료일
    private int days; // 연차 일수

    private LeaveRequest(String id, String title, String content, Employee author, String status,
            LocalDateTime createdDateTime, LocalDateTime modifiedDateTime, List<Approve> approves,
            String leaveType, LocalDate startDate, LocalDate endDate, int days) {
        super(id, title, content, author, status, createdDateTime, modifiedDateTime, approves);
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public static LeaveRequest create(IdFactory idFactory, String title, String content, Employee author, List<Approve> approves,
            String leaveType, LocalDate startDate, LocalDate endDate, int days, Clock clock) {
        return new LeaveRequest(idFactory.generateDocumentId(), title, content, author, "대기", LocalDateTime.now(clock), null, approves,
                leaveType, startDate, endDate, days);
    }
}
