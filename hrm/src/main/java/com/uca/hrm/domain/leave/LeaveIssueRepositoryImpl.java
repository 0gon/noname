package com.uca.hrm.domain.leave;

import com.uca.hrm.domain.leave.model.LeaveIssue;
import com.uca.hrm.domain.leave.port.LeaveIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LeaveIssueRepositoryImpl implements LeaveIssueRepository {

    private final LeaveIssueRepositoryJpa leaveIssueRepositoryJpa;

    @Override
    public LeaveIssue save(LeaveIssue leaveIssue) {
        return leaveIssueRepositoryJpa.save(leaveIssue);
    }
}
