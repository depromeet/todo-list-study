package com.depromeet.todolist.exception;

import com.depromeet.todolist.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
}
