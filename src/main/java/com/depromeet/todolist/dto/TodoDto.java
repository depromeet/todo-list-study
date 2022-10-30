package com.depromeet.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class TodoDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long todoId;
        private String title;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final Long id;
        private final String title;
    }

}
