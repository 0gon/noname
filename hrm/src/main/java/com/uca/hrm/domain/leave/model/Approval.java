package com.uca.hrm.domain.leave.model;

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
public class Approval {

    @Id
    String id;
    @ManyToOne
    Employee approval;
    int order; // 결재 순서
    String comment;
    LocalDateTime approvalDate;


}
