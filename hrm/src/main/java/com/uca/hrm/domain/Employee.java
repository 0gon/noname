
package com.uca.hrm.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import com.uca.hrm.comm.exception.InvalidException;
import com.uca.hrm.domain.util.IdFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Employee extends BaseField {

    @Id
    private String id;
    private String firstName; // 이름
    private String lastName; // 성
    private String email;
    private String phoneNumber;
    private LocalDate hireDate; // 입사일
    private String department; // 소속
    private String jobTitle; // 직책
    private BigDecimal salary; // 연봉

    @OneToMany(mappedBy = "employee")
    private List<LeaveIssue> leaveIssues; // 연차 발급 내역
    @OneToMany(mappedBy = "employee")
    private List<LeaveRequest> leaveRequests; // 연차 신청 내역

    // 신규 생성
    public static Employee create(String firstName, String lastName, String email, String phoneNumber,
            LocalDate hireDate, String department, String jobTitle, BigDecimal salary) {

        return new Employee(firstName, lastName, email, phoneNumber, hireDate, department, jobTitle, salary);
    }

    protected Employee() {
        super();
    }

    private Employee(String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate,
            String department, String jobTitle,
            BigDecimal salary) {
        super();

        this.id = IdFactory.generateEmployeeId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;

        validation();
    }

    @PrePersist
    @PostLoad
    public void jpaValidation() {
        validation();
    }

    private void validation() {

        // 필수값 체크
        if (this.firstName == null || this.firstName.isEmpty()) {
            throw new InvalidException("First name is required");
        }

        if (this.lastName == null || this.lastName.isEmpty()) {
            throw new InvalidException("Last name is required");
        }

        if (this.hireDate == null) {
            throw new InvalidException("Hire date is required");
        }

        if (this.salary == null) {
            throw new InvalidException("Salary is required");
        }

        // 이메일 형식 체크
        if (this.email == null || this.email.isEmpty()) {
            throw new InvalidException("Email is required");
        }

        boolean valid = EmailValidator.getInstance().isValid(this.email);
        if (!valid) {
            throw new InvalidException("Invalid email address");
        }
    }

    // 근속 연수 가져오기
    public int getYearsOfService() {
        if (hireDate == null) {
            throw new InvalidException("Hire date is required");
        }
        return (int) ChronoUnit.YEARS.between(hireDate, LocalDate.now());
    }

}
