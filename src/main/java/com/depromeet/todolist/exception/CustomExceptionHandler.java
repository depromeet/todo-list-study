package com.depromeet.todolist.exception;

import com.depromeet.todolist.exception.customExcption.DuplicatedUserExcption;
import com.depromeet.todolist.exception.customExcption.TodoNotFoundException;
import com.depromeet.todolist.exception.customExcption.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT, "존재하지 않는 사용");
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(DuplicatedUserExcption.class)
    public ResponseEntity<ErrorResponse> handlerDuplicatedUserException(DuplicatedUserExcption e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, "중복된 사용자 존재");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerTodoNotFoundException(TodoNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT, "존재하지 않는 TODO");
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }
}
