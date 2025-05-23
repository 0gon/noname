package com.uca.hrm.domain.leave;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.hrm.comm.util.IdFactory;
import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.port.EmployeeRepository;
import com.uca.hrm.domain.leave.model.LeaveIssue;
import com.uca.hrm.domain.leave.port.LeaveIssueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LeaveIssueService {

    private final EmployeeRepository employeeRepository;
    private final LeaveIssueRepository leaveIssueRepository;
    private final IdFactory idFactory;
    private final Clock clock;

    // 사원들의 근속 연수를 계산하여 연차를 부여하는 로직
    public void grant() {
        // 1. 지급 대상자 조회
        List<Employee> employees = employeeRepository.findByLeaveRecipient();

        for (Employee employee : employees) {
            // 2. 각 사원의 근속 연수 계산
            int yearsOfService = employee.getYearsOfService(clock);

            // 3. 근속 연수에 따라 연차 부여
            int annualLeave = calculateAnnualLeave(yearsOfService, employee);

            if(annualLeave <= 0) {
                continue;
            }
            LeaveIssue leaveIssue = LeaveIssue.generate(idFactory, employee, "연차", annualLeave);

            // 4. 연차 부여 결과 저장
            leaveIssueRepository.save(leaveIssue);
        }

    }

    private int calculateAnnualLeave(int yearsOfService, Employee employee) {
        // todo: Projection 인터페이스 를 사용하여 연차 계산하기

        LocalDate today = LocalDate.now(clock);

        if (yearsOfService < 1) { // 1년 미만
            // int absentDays = attendanceLogRepository.getAbsentsByMonth().size();

            // 지급 계산 다음날 새벽이기에 하루 빼고 계산
            LocalDate grantStart = today.minusDays(1).minusMonths(1);
            LocalDate grantEnd = today.minusDays(1);

            long absentDays = employee.getAttendanceLogs().stream()
                    .filter(log -> {
                        if (!log.getStatus().equals("결근")) {
                            return false;
                        } else {
                            LocalDate absentDate = log.getDate();
                            if ((absentDate.isEqual(grantStart) || absentDate.isAfter(grantStart)) &&
                                    (absentDate.isBefore(grantEnd) || absentDate.isEqual(grantEnd))) {
                                return true;
                            }
                            return false;
                        }
                    })
                    .count();

            if (absentDays > 0) { // 결근 1일 이상 연차 0일 지급
                return 0;
            } else {
                return 1;
            }

        } else { // 1년 이상
            // int absentDays = attendanceLogRepository.getAbsentsByYear().size();
            // 지급 계산 다음날 새벽이기에 하루 빼고 계산
            LocalDate grantStart = today.minusDays(1).minusYears(1);
            LocalDate grantEnd = today.minusDays(1);

            long absentDays = employee.getAttendanceLogs().stream()
                    .filter(log -> {
                        if (!log.getStatus().equals("결근")) {
                            return false;
                        } else {
                            LocalDate absentDate = log.getDate();
                            if ((absentDate.isEqual(grantStart) || absentDate.isAfter(grantStart)) &&
                                    (absentDate.isBefore(grantEnd) || absentDate.isEqual(grantEnd))) {
                                return true;
                            }
                            return false;
                        }
                    })
                    .count();

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

    public LeaveIssue caclulateAndGenerate(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caclulateAndGenerate'");
    }

    public List<LeaveIssue> findByPossibleLeave(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByPossibleLeave'");
    }
}
