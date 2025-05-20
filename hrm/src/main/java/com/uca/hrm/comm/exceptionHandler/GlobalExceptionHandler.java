package com.uca.hrm.comm.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uca.hrm.comm.exception.InvalidException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<String> handleInvalidException(InvalidException ex) {
        // 로그에는 간단한 예외 메시지 + 가장 위 stack trace 하나만 출력
        StackTraceElement top = ex.getStackTrace()[0];
        log.error("{} at {}:{}", ex.getMessage(), top.getClassName(), top.getLineNumber());

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage()); // 클라이언트엔 메시지만 전송
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // 로그에는 간단한 예외 메시지 + 가장 위 stack trace 하나만 출력
        StackTraceElement top = ex.getStackTrace()[0];
        log.error("{} at {}:{}", ex.getMessage(), top.getClassName(), top.getLineNumber());

        ex.printStackTrace(); // 전체 stack trace는 콘솔에 출력

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage()); // 클라이언트엔 메시지만 전송
    }
}
