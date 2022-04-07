package com.depromeet.todolist.todo.dto;

import com.depromeet.todolist.todo.TodoType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoRequest {

    private String content;
    private TodoType type;

}
