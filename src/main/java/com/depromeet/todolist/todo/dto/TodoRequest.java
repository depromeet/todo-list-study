package com.depromeet.todolist.todo.dto;

import com.depromeet.todolist.todo.TodoType;
import lombok.Data;

@Data
public class TodoRequest {

    private String content;
    private TodoType type;

    public TodoRequest(String content, TodoType type) {
        this.content = content;
        this.type = type;
    }
}
