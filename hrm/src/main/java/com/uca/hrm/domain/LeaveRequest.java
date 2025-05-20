package com.uca.hrm.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class LeaveRequest {

    @Id
    private String id;
    private Employee employee; // 사원 ID
    private String leaveType; // 연차 종류
    private LocalDate startDate; // 연차 시작일
    private LocalDate endDate; // 연차 종료일
    private int days; // 연차 일수
    private String status; // 연차 상태 (예: 승인, 반려 등)
    private String reason; // 연차 사유
    private String approverId; // 결재자 ID
    private LocalDate approvalDate; // 결재일
    private String comments; // 결재자 코멘트

}
