package com.uca.hrm.domain.leave.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uca.hrm.domain.leave.model.LeaveIssue;
import com.uca.hrm.domain.leave.port.LeaveIssueRepository;

public class FakeLeaveIssueRepository implements LeaveIssueRepository {

    public List<LeaveIssue> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public LeaveIssue save(LeaveIssue leaveIssue) {
        data.removeIf(e -> e.getId().equals(leaveIssue.getId()));
        data.add(leaveIssue);
        return leaveIssue;
    }

}
