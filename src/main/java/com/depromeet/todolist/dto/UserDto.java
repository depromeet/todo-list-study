package com.depromeet.todolist.dto;

import lombok.*;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String userId;
        private String name;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final String userId;
        private final String name;
    }

}
