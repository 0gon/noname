package com.uca.hrm.domain;

import java.util.concurrent.atomic.AtomicInteger;


class EmployeeIdFactory {
    
    private static AtomicInteger idCounter = new AtomicInteger(0);

    static {
        // todo: Initialize the idCounter from the database
    }

    static String generateId() {
        int id = idCounter.incrementAndGet();
        String currentDate = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"));
        return currentDate + String.format("%04d", id);
    }


}
