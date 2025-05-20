package com.uca.hrm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.hrm.batch.port.AnnualLeaveService;
import com.uca.hrm.domain.Employee;
import com.uca.hrm.domain.LeaveIssue;
import com.uca.hrm.service.port.AttendanceLogRepository;
import com.uca.hrm.service.port.EmployeeRepository;
import com.uca.hrm.service.port.LeaveIssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnualLeaveServiceImpl implements AnnualLeaveService{

    private final EmployeeRepository employeeRepository;
    private final LeaveIssueRepository leaveIssueRepository;
    private final AttendanceLogRepository attendanceLogRepository;


    // 사원들의 근속 연수를 계산하여 연차를 부여하는 로직
    public void grant() {
        // 1. 지급 대상자 조회
        List<Employee> employees = employeeRepository.findByLeaveRecipient();

        for (Employee employee : employees) {
            // 2. 각 사원의 근속 연수 계산
            int yearsOfService = employee.getYearsOfService();

            // 3. 근속 연수에 따라 연차 부여
            int annualLeave = calculateAnnualLeave(yearsOfService);

            LeaveIssue leaveIssue = LeaveIssue.create(employee, "연차", annualLeave);

            // 4. 연차 부여 결과 저장
            leaveIssueRepository.save(leaveIssue);
        }

    }



    private int calculateAnnualLeave(int yearsOfService) {
        if (yearsOfService < 1) { // 1년 미만
            int absentDays = attendanceLogRepository.getAbsentsByMonth().size();
            if (absentDays > 0) { // 결근 1일 이상 연차 0일 지급
                return 0;
            } else {
                return 1;
            }


        } else { // 1년 이상
            int absentDays = attendanceLogRepository.getAbsentsByMonth().size();
            if (absentDays > 30) { // 결근 30일 이상 연차 0일 지급
                return 0;
            }

            if (yearsOfService < 3) { // 1년 이상 3년 미만
                return 15;
            } else if (yearsOfService < 5) { // 3년 이상 5년 미만
                return 20;
            } else if (yearsOfService < 10) { // 5년 이상 10년 미만
                return 25;
            } else { // 10년 이상
                return 30;
            }
        }
    }
}
