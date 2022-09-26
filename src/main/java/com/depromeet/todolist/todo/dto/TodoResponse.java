package com.depromeet.todolist.todo.dto;

import com.depromeet.todolist.todo.Todo;
import com.depromeet.todolist.todo.TodoType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoResponse {

    private String content;
    private TodoType type;
}
