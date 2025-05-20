package com.uca.hrm.batch;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uca.hrm.batch.port.AnnualLeaveService;

import lombok.RequiredArgsConstructor;

// 매일 새벽 3시에 사원들의 근속 연수를 계산하여 연차를 부여하는 배치 프로그램
@EnableScheduling
@Component
@EnableAsync
@RequiredArgsConstructor
public class AnnualLeaveBatch {

    private final AnnualLeaveService leaveBatchService;

    // @Scheduled(cron = "0 0 3 * * ?")
    public void execute() {
        leaveBatchService.grant();
    }


}
