package com.example.PayFlow.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse (
        String errorCode,
        String erroDescription,
        LocalDateTime timestamp,
        List<FieldError> fieldErrors
){
    public record FieldError(String field, String message) { }
    public static  ErrorResponse of(String errorCode, String erroDescription, LocalDateTime timestamp, List<FieldError> fieldErrors){
        return new ErrorResponse(errorCode, erroDescription, LocalDateTime.now(), fieldErrors);
    }
    public static ErrorResponse of(String errorCode, String erroDescription){
        return new ErrorResponse(errorCode,erroDescription,LocalDateTime.now(),null);
    }
}
