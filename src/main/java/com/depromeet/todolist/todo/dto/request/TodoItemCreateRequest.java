package com.depromeet.todolist.todo.dto.request;

public record TodoItemCreateRequest(
        String content,
        boolean finished) {
}
