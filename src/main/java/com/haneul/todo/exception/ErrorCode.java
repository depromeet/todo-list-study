package com.haneul.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_TODO_FOUND("TODO-ERR-404", "TODO NOT FOUND"),
    BAD_REQUEST("COMMON-ERR-400", "BAD REQUEST");

    private String errorCode;
    private String message;
}
