package com.uca.hrm.comm.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class IdFactoryImpl implements IdFactory {

    private AtomicInteger employeeIdCounter = new AtomicInteger(0);
    private AtomicInteger leaveIssueIdCounter = new AtomicInteger(0);

    static {
        // todo: Initialize the idCounter from the database
    }

    public String generateEmployeeId() {
        int id = employeeIdCounter.incrementAndGet();
        return getCurrentDate() + String.format("%04d", id);
    }

    public String generateLeaveIssueId() {
        int id = leaveIssueIdCounter.incrementAndGet();
        return getCurrentDate() + String.format("%04d", id);
    }

    public String generateAttendanceLogId() {
        int id = leaveIssueIdCounter.incrementAndGet();
        return getCurrentDate() + String.format("%04d", id);
    }

    private String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
    }
}
