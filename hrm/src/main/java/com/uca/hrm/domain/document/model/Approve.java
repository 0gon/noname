package com.uca.hrm.domain.document.model;

import com.uca.hrm.domain.employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class Approve {

    @Id
    String id;

    @ManyToOne
    Document document; // 문서 번호

    @ManyToOne
    Employee approver;
    
    int order; // 결재 순서
    String status; // 결재 상태 (예: 승인, 반려 등)
    String comment;
    LocalDateTime approvalDate;

}
