package com.depromeet.todolist.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class TodoRequest {
    @NonNull
    private String title;

    @NonNull
    private Long order;

    @NonNull
    private Boolean completed;
}
