package com.uca.hrm.infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "employee")
@Getter
public class EmployeeEntity extends BaseFieldEntity {

    @Id
    private String id;
    private String firstName; // 이름
    private String lastName; // 성
    private String email;
    private String phoneNumber;
    private String hireDate; // 입사일
    private String department; // 소속
    private String jobTitle; // 직책
    private double salary; // 연봉


}
