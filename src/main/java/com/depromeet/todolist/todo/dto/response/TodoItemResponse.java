package com.depromeet.todolist.todo.dto.response;

import java.util.UUID;

import com.depromeet.todolist.todo.domain.Todo;

public record TodoItemResponse(
        UUID id,
        String content,
        boolean finished) {

    public TodoItemResponse(Todo todo) {
        this(todo.getId(), todo.getContent(), todo.isFinished());
    }
}
