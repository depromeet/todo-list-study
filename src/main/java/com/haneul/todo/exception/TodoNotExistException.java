package com.haneul.todo.exception;

import lombok.Getter;

@Getter
public class TodoNotExistException extends RuntimeException{

    private ErrorCode errorCode;

    public TodoNotExistException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
