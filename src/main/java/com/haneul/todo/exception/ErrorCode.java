package com.haneul.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_TODO_FOUND("TODO-ERR-404", "TODO NOT FOUND"),
    INTER_SERVER_ERROR("COMMON-ERR-500", "INTER SERVER ERROR");

    private String errorCode;
    private String message;
}
