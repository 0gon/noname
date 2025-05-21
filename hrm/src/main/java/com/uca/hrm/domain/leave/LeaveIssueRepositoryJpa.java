package com.uca.hrm.domain.leave;

import com.uca.hrm.domain.leave.model.LeaveIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveIssueRepositoryJpa extends JpaRepository<LeaveIssue, String> {
}
