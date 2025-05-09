package com.uca.hrm.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
class BaseField {

    private boolean isActive; // 사용여부
    private LocalDateTime createdDate; // 등록일
    private LocalDateTime modifiedDate; // 수정일

    BaseField() {
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = null;
    }

    void activate() {
        this.isActive = true;
        this.modifiedDate = LocalDateTime.now();
    }

    void deativate() {
        this.isActive = false;
        this.modifiedDate = LocalDateTime.now();
    }
}
