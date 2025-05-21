package com.uca.hrm.domain.leave.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import com.uca.hrm.domain.leave.LeaveIssueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.uca.hrm.comm.util.FakeIdFactory;
import com.uca.hrm.domain.attendanceLog.AttendanceLog;
import com.uca.hrm.domain.attendanceLog.port.AttendanceLogRepository;
import com.uca.hrm.domain.attendanceLog.FakeAttendanceLogRepository;
import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.repository.FakeEmployeeRepository;
import com.uca.hrm.domain.leave.model.LeaveIssue;
import com.uca.hrm.domain.leave.infra.FakeLeaveIssueRepository;

public class LeaveIssueServiceTest {

    private LeaveIssueService leaveIssueService;
    private FakeEmployeeRepository employeeRepository;
    private FakeLeaveIssueRepository leaveIssueRepository;
    AttendanceLogRepository attendanceLogRepository;
    FakeIdFactory idFactory;

    @BeforeEach
    void setUp() {
        Clock clock = Clock.fixed(LocalDate.of(2024, 05, 31).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault());
        employeeRepository = new FakeEmployeeRepository(clock);
        leaveIssueRepository = new FakeLeaveIssueRepository();
        idFactory = new FakeIdFactory();
        attendanceLogRepository = new FakeAttendanceLogRepository();

        leaveIssueService = new LeaveIssueService(employeeRepository, leaveIssueRepository, idFactory, clock);

    }

    @Test
    void testFindByLeaveRecipient() {
        // given
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 04, 01), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2021, 03, 01), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 04, 30), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 03, 31), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2021, 05, 31), "d", "j", new BigDecimal(2)));

        // when
        List<Employee> byLeaveRecipient = employeeRepository.findByLeaveRecipient();

        // then
        assertEquals(2, byLeaveRecipient.size());

    }

    @Test
    void testGrant() {
        // Given
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 04, 01), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2021, 03, 01), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 04, 30), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 03, 31), "d", "j", new BigDecimal(2)));
        employeeRepository.save(Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2021, 05, 31), "d", "j", new BigDecimal(2)));

        // When
        leaveIssueService.grant();

        // Then
        int size = leaveIssueRepository.data.size();
        assertEquals(2, size);
        assertEquals(1, leaveIssueRepository.data.get(0).getDays());
        assertEquals(20, leaveIssueRepository.data.get(1).getDays());

    }

    @Test
    void _1년차미만_결근없으면_연차하루지급() {
        // Given
        Employee employee = Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 03, 31), "d", "j", new BigDecimal(2));
        Employee saved = employeeRepository.save(employee);

        // When
        leaveIssueService.grant();

        // Then
        assertEquals(1, leaveIssueRepository.data.size());
        LeaveIssue leaveIssue = leaveIssueRepository.data.get(0);
        assertEquals(1, leaveIssue.getDays());
    }


    @Test
    void _1년차미만_하루이상_결근시_연차미지급() {
        // Given
        Employee employee = Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2024, 03, 31), "d", "j", new BigDecimal(2));
        AttendanceLog attendanceLog = AttendanceLog.create(idFactory, employee, "결근",
                LocalDate.of(2024, 05, 01), "00:00");

        employee.getAttendanceLogs().add(attendanceLog);
        Employee saved = employeeRepository.save(employee);

        // When
        leaveIssueService.grant();

        // Then
        assertEquals(0, leaveIssueRepository.data.size());
    }



    @Test
    void _1년차이상_하루이상_결근해도_연차_15일지급() {
        // Given
        Employee employee = Employee.create(idFactory, "f", "l", "johnoe@example.com", "0",
                LocalDate.of(2023, 05, 31), "d", "j", new BigDecimal(2));
        AttendanceLog attendanceLog = AttendanceLog.create(idFactory, employee, "결근",
                LocalDate.of(2024, 05, 01), "00:00");

        employee.getAttendanceLogs().add(attendanceLog);
        Employee saved = employeeRepository.save(employee);

        // When
        leaveIssueService.grant();

        // Then
        assertEquals(1, leaveIssueRepository.data.size());
        LeaveIssue leaveIssue = leaveIssueRepository.data.get(0);
        assertEquals(15, leaveIssue.getDays());
    }
}
