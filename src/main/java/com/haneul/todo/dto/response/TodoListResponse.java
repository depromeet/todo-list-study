package com.haneul.todo.dto.response;

import java.util.List;

public record TodoListResponse(
        List<TodoResponse> todoResponses
) {}
