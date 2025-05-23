package com.uca.hrm.domain.employee.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.port.EmployeeRepository;
import com.uca.hrm.domain.leave.LeaveIssueService;
import com.uca.hrm.domain.leave.model.LeaveIssue;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeLeaveService {

    private final EmployeeRepository employeeRepository;
    private final LeaveIssueService leaveIssuService;

    /**
     * 사원에게 휴가를 부여하는 메소드
     */
    public void grantVacation() {
        List<Employee> targets = employeeRepository.findByLeaveRecipient();
        for (Employee employee : targets) {
            // 제공할 휴가 일수 계산
            LeaveIssue leaveIssue = leaveIssuService.caclulateAndGenerate(employee);

            // 사원이 휴가 받아들임
            employee.acceptLeaveIssue(leaveIssue);
            employeeRepository.save(employee);
        }
    }
}
