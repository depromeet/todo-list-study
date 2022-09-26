package com.depromeet.todolist.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private int defaultCode;

    public BusinessException(int errorCode) {
        this.defaultCode = errorCode;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.defaultCode = -1;
    }

    public BusinessException(String message, int errorCode) {
        super(message);
        this.defaultCode = errorCode;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.defaultCode = -1;
    }

    public BusinessException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.defaultCode = errorCode;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.defaultCode = -1;
    }

    public BusinessException(Throwable cause, int errorCode) {
        super(cause);
        this.defaultCode = errorCode;
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.defaultCode = -1;
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.defaultCode = errorCode;
    }
}
