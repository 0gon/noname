package com.uca.hrm;

import java.time.Clock;
import java.util.List;

import javax.print.Doc;

import org.springframework.stereotype.Service;

import com.uca.hrm.comm.util.IdFactory;
import com.uca.hrm.domain.document.model.Document;
import com.uca.hrm.domain.document.model.LeaveRequest;
import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.port.EmployeeRepository;
import com.uca.hrm.domain.leave.LeaveIssueService;
import com.uca.hrm.domain.leave.model.LeaveIssue;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Server {

    private final EmployeeRepository employeeRepository;
    private final LeaveIssueService leaveIssueService;

    /**
     * 사원이 휴가를 사용하는 메소드
     */
    public void useLeave(String emplId, LeaveRequest leaveRequest) {
        // 사원 조회
        Employee employee = employeeRepository.findById(emplId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        if (leaveRequest.getLeaveType().equals("연차")) {
            // 사용 가능한 휴가 조회
            List<LeaveIssue> leaveIssues = leaveIssueService.findByPossibleLeave(employee);
            int sum = leaveIssues.stream().mapToInt(LeaveIssue::getRemainingDays).sum();

            if (sum < leaveRequest.getDays()) {
                throw new IllegalArgumentException("Not enough leave days");
            }

            // todo
        }

        employeeRepository.save(employee);
    }








    private final IdFactory idFactory;
    private final Clock clock;

    /**
     * 연차 신청서 처리
     */
    public void leaveReq(LeaveReq leaveReq) {

        Employee author = employeeRepository.findById(leaveReq.emplId());
        if (author == null) {
            throw new IllegalArgumentException("Author not found");
        }

        // 연차 신청서 생성
        LeaveRequest leaveRequest = LeaveRequest.create(null, null, null, author, null, null, null, null, 0, null)

    }


}
