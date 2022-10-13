package com.depromeet.todolist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@ToString
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String message;
}
