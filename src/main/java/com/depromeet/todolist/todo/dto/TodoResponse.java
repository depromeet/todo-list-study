package com.depromeet.todolist.todo.dto;

import com.depromeet.todolist.todo.Todo;
import com.depromeet.todolist.todo.TodoType;
import lombok.Data;

@Data
public class TodoResponse {

    private String content;
    private TodoType type;

    public TodoResponse(String content, TodoType type) {
        this.content = content;
        this.type = type;
    }


    static public TodoResponse of(Todo todo) {
        return new TodoResponse(todo.getContent(), todo.getType());
    }
}
