package com.depromeet.todolist.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorDetail;


    @Builder
    public BusinessException(ErrorCode errorCode, String errorDetail) {
        this.errorCode = errorCode;
        this.errorDetail = errorDetail;
    }
}
