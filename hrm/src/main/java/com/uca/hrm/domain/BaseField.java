package com.uca.hrm.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class BaseField {

    private boolean isActive; // 사용여부
    private LocalDateTime createdDate; // 등록일
    private LocalDateTime modifiedDate; // 수정일

    protected BaseField() {
        this.isActive = true;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = null;
    }

    public void activate() {
        this.isActive = true;
        this.modifiedDate = LocalDateTime.now();
    }

    public void deativate() {
        this.isActive = false;
        this.modifiedDate = LocalDateTime.now();
    }
}
