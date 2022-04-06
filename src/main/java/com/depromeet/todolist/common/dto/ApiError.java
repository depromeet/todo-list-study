package com.depromeet.todolist.common.dto;

import com.depromeet.todolist.exception.ApplicationException;
import org.springframework.http.HttpStatus;

/** 모든 API 반환 형식의 통일을 위한 Error DTO */
public class ApiError {

    private final String message;

    private final int status;

    public ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getMessage(), status);
    }

    public ApiError(ApplicationException applicationException) {
        this(applicationException.getMessage(), applicationException.getErrorCode());
    }

    public ApiError(String errorMessage, HttpStatus status) {
        this.message = errorMessage;
        this.status = status.value();
    }

    public ApiError(String errorMessage, int errorCode) {
        this.message = errorMessage;
        this.status = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
