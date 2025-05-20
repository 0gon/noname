package com.uca.hrm.domain;

import java.time.LocalDate;

import com.uca.hrm.domain.util.IdFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class LeaveIssue extends BaseField {

    @Id
    private String id;
    @ManyToOne
    private Employee employee; // 사원 ID
    private String leaveType; // 연차 종류

    private int days; // 지급 일수
    private int remainingDays; // 잔여 연차 일수

    private boolean isExpired; // 소멸 여부

    protected LeaveIssue() {
        super();
    }

    private LeaveIssue(String id, Employee employee, String leaveType, int days, boolean isExpired) {
        super();
        this.id = id;
        this.employee = employee;
        this.leaveType = leaveType;
        this.days = days;
        this.remainingDays = days;
        this.isExpired = isExpired;
    }

    public static LeaveIssue create(Employee employee, String leaveType, int days) {
        return new LeaveIssue(IdFactory.generateLeaveIssueId(), employee, leaveType, days, false);
    }

}
