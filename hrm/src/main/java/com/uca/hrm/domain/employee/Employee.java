
package com.uca.hrm.domain.employee;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import com.uca.hrm.comm.exception.InvalidException;
import com.uca.hrm.comm.util.IdFactory;
import com.uca.hrm.domain.attendanceLog.AttendanceLog;
import com.uca.hrm.domain.document.model.Document;
import com.uca.hrm.domain.document.model.LeaveRequest;
import com.uca.hrm.domain.leave.model.LeaveIssue;
import com.uca.hrm.domain.util.BaseField;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "author")
    private List<Document> documents = new ArrayList<>();; // 문서 작성 내역

    @OneToMany(mappedBy = "employee")
    private List<AttendanceLog> attendanceLogs = new ArrayList<>(); // 출결 내역

    @OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<LeaveIssue> leaveIssues = new ArrayList<>();; // 연차 발급 내역
    

    // 신규 생성
    public static Employee create(IdFactory idFactory, String firstName, String lastName, String email,
            String phoneNumber,
            LocalDate hireDate, String department, String jobTitle, BigDecimal salary) {

        return new Employee(idFactory.generateEmployeeId(), firstName, lastName, email, phoneNumber, hireDate,
                department, jobTitle, salary);
    }

    protected Employee() {
        super();
    }

    private Employee(String id, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate,
            String department, String jobTitle,
            BigDecimal salary) {
        super();

        this.id = id;
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
    public int getYearsOfService(Clock clock) {
        if (hireDate == null) {
            throw new InvalidException("Hire date is required");
        }
        return (int) ChronoUnit.YEARS.between(hireDate, LocalDate.now(clock));
    }

    public void update(String firstName2, String lastName2, String email2, String phoneNumber2, LocalDate hireDate2,
            String department2, String jobTitle2, BigDecimal salary2) {
        if (firstName2 != null && !firstName2.isEmpty()) {
            this.firstName = firstName2;
        }
        if (lastName2 != null && !lastName2.isEmpty()) {
            this.lastName = lastName2;
        }
        if (email2 != null && !email2.isEmpty()) {
            this.email = email2;
        }
        if (phoneNumber2 != null && !phoneNumber2.isEmpty()) {
            this.phoneNumber = phoneNumber2;
        }
        if (hireDate2 != null) {
            this.hireDate = hireDate2;
        }
        if (department2 != null && !department2.isEmpty()) {
            this.department = department2;
        }
        if (jobTitle2 != null && !jobTitle2.isEmpty()) {
            this.jobTitle = jobTitle2;
        }
        if (salary2 != null) {
            this.salary = salary2;
        }
        validation();
    }

    public void acceptLeaveIssue(LeaveIssue leaveIssue) {
        if (leaveIssue == null) {
            throw new InvalidException("Leave issue is required");
        }
        leaveIssues.add(leaveIssue);
    }

    public void useLeave() {

    }

}
