package com.uca.hrm.domain.leave.model;

import java.time.LocalDate;
import java.util.List;

import com.uca.hrm.domain.employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class LeaveRequest {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn
    private Employee employee; // 사원 ID
    private String leaveType; // 연차 종류
    private LocalDate startDate; // 연차 시작일
    private LocalDate endDate; // 연차 종료일
    private int days; // 연차 일수
    private String status; // 연차 상태 (예: 승인, 반려 등)
    private String reason; // 연차 사유
    List<Approval> approvals;

}

