package com.depromeet.todolist.todo.dto.request;

public record TodoItemUpdateRequest(
                String content,
                boolean finished) {
}
