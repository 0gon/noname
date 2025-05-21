package com.uca.hrm.comm.util;

public class FakeIdFactory implements IdFactory {

    private int employeeIdCounter = 0;
    private int leaveIssueIdCounter = 0;
    private int attendanceLogIdCounter = 0;

    @Override
    public String generateEmployeeId() {
        employeeIdCounter++;
        return String.format("E%04d", employeeIdCounter);
    }

    @Override
    public String generateLeaveIssueId() {
        leaveIssueIdCounter++;
        return String.format("L%04d", leaveIssueIdCounter);
    }

    @Override
    public String generateAttendanceLogId() {
        attendanceLogIdCounter++;
        return String.format("A%04d", attendanceLogIdCounter);
    }

}
