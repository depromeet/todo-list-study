package com.depromeet.todolist.exception;

import com.depromeet.todolist.common.dto.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.depromeet.todolist.common.dto.ApiResult.ERROR;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable, status), headers, status);
    }

    private ResponseEntity<ApiResult<?>> newApplicationResponse(ApplicationException applicationException, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(applicationException), headers, status);
    }

    @ExceptionHandler({Throwable.class, RuntimeException.class})
    public ResponseEntity<ApiResult<?>> handleApplicationException(Exception e) {
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
