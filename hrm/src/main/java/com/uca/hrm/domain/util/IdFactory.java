package com.uca.hrm.domain.util;

import java.util.concurrent.atomic.AtomicInteger;


public class IdFactory {
    
    private static AtomicInteger employeeIdCounter = new AtomicInteger(0);
    private static AtomicInteger leaveIssueIdCounter = new AtomicInteger(0);

    static {
        // todo: Initialize the idCounter from the database
    }

    public static String generateEmployeeId() {
        int id = employeeIdCounter.incrementAndGet();
        String currentDate = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"));
        return currentDate + String.format("%04d", id);
    }

    public static String generateLeaveIssueId() {
        int id = leaveIssueIdCounter.incrementAndGet();
        String currentDate = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"));
        return currentDate + String.format("%04d", id);
    }


}
