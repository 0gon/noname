package com.uca.hrm.infra;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseFieldEntity {

    private boolean isActive; // 사용여부
    private LocalDateTime createdDate; // 등록일
    private LocalDateTime modifiedDate; // 수정일


    
}
