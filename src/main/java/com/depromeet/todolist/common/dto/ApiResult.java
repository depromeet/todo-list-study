package com.depromeet.todolist.common.dto;

import com.depromeet.todolist.exception.ApplicationException;
import org.springframework.http.HttpStatus;

/** 모든 API 반환 형식의 통일을 위한 DTO */
public class ApiResult<T> {

    private final boolean success;
    private final T response;
    private final ApiError error;

    private ApiResult(boolean success, T response, ApiError error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }

    public static <T> ApiResult<T> OK(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> ERROR(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(throwable, status));
    }

    public static ApiResult<?> ERROR(ApplicationException applicationException) {
        return new ApiResult<>(false, null, new ApiError(applicationException));
    }

    public static ApiResult<?> ERROR(String errorMessage, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(errorMessage, status));
    }

    public static <T> ApiResult<T> ERROR(T response, String errorMessage, HttpStatus status) {
        return new ApiResult<>(false, response, new ApiError(errorMessage, status));
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public ApiError getError() {
        return error;
    }
}