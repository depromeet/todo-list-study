package com.depromeet.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER(HttpStatus.CONFLICT, "User Duplicated"),
    NO_TODO(HttpStatus.NO_CONTENT, "No Todo"),
    NO_USER(HttpStatus.NO_CONTENT, "No USER")
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
