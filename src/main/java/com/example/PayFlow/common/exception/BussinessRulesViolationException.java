package com.example.PayFlow.common.exception;

import lombok.Getter;

@Getter
public class BussinessRulesViolationException extends RuntimeException {
    private final String errorCode;

    public BussinessRulesViolationException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
