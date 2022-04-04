package com.haneul.todo.dto.response;

import com.haneul.todo.entity.Todo;
import com.haneul.todo.entity.Status;


public record TodoResponse(
        Long id,
        String title,
        String content,
        Status status
) {
    public TodoResponse(Todo todo) {
        this(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getStatus()
        );
    }
}
