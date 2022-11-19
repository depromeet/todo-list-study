package com.depromeet.todolist.error;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 404 NOT_FOUND  */
    TODO_NOT_FOUND(NOT_FOUND, "해당 todo를 찾을 수 없습니다"),

    /* 409 CONFLICT  */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다");

    private final HttpStatus httpStatus;
    private final String detail;
}
