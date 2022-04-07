package com.depromeet.todolist.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TodoResponseDto {

    private List<TodoResponse> todoResponses;
}
