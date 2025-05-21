package com.uca.hrm.domain.employee.repository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uca.hrm.domain.employee.Employee;
import com.uca.hrm.domain.employee.port.EmployeeRepository;

public class FakeEmployeeRepository implements EmployeeRepository {

    private final List<Employee> data = Collections.synchronizedList(new ArrayList<>());
    private Clock clock;

    public FakeEmployeeRepository(Clock clock) {
        this.clock = clock;
    }

    @Override
    public Employee save(Employee employee) {
        data.removeIf(e -> e.getId().equals(employee.getId()));
        data.add(employee);
        return employee;
    }

    @Override
    public Employee findById(String id) {
        return data.stream()
                .filter(employee -> employee.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Employee> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Employee> findByLeaveRecipient() {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : data) {
            LocalDate hireDate = employee.getHireDate();
            LocalDate fixedToday = LocalDate.of(2024, 05, 31);
            if (employee.getYearsOfService(clock) < 1) {
                boolean b = isCustomSameDay(hireDate, fixedToday);
                if (b) {
                    result.add(employee);
                }
            } else {
                boolean b = isCustomSameDay(hireDate.plusYears(1), fixedToday);
                boolean b2 = hireDate.getMonth() == fixedToday.getMonth();
                if (b && b2) {
                    result.add(employee);
                }
            }
        }
        return result;
    }

    public boolean isCustomSameDay(LocalDate a, LocalDate b) {
        int aDay = a.getDayOfMonth();
        int bDay = b.getDayOfMonth();
        int bMonthLength = b.lengthOfMonth();

        // a가 b의 마지막 날을 "초과한 날짜"이고, b에는 해당 일이 없음 → 말일 취급
        if (bDay == bMonthLength && aDay > bMonthLength) {
            return true;
        }

        // 그 외에는 day 정확히 같아야 함
        return aDay == bDay;
    }

}
