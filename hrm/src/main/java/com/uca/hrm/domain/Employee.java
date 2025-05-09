
package com.uca.hrm.domain;

import java.math.BigDecimal;

import com.uca.hrm.comm.Global;

import jakarta.validation.Validator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import lombok.val;


@Getter
@ToString
class Employee extends BaseField {

    @NotNull
    private String id;

    @NotNull
    private String firstName; // 이름

    @NotNull
    private String lastName; // 성

    @Email
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String hireDate; // 입사일

    private String department; // 소속

    private String jobTitle; // 직책

    @NotNull
    private BigDecimal salary; // 연봉

    // 신규 생성
    public static Employee create(String firstName, String lastName, String email, String phoneNumber, String hireDate, String department,
            String jobTitle, BigDecimal salary) {

        return new Employee(firstName, lastName, email, phoneNumber, hireDate, department, jobTitle, salary);
    }

    private Employee(String firstName, String lastName, String email, String phoneNumber, String hireDate, String department, String jobTitle,
            BigDecimal salary) {
        super();

        this.id = EmployeeIdFactory.generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;


        Global.validate(this);
    }

}
