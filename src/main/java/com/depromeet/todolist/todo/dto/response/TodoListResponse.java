package com.depromeet.todolist.todo.dto.response;

import java.util.List;
import java.util.UUID;

import com.depromeet.todolist.todo.domain.Todo;

public record TodoListResponse(
        List<TodoResponse> todos) {

    public record TodoResponse(
            UUID id,
            String content,
            boolean finished) {

        public TodoResponse(Todo todo) {
            this(todo.getId(), todo.getContent(), todo.isFinished());
        }
    }
}
