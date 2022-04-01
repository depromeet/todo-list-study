package com.haneul.todo.dto.response;

import com.haneul.todo.entity.Todo;
import com.haneul.todo.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private String content;
    private Status status;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.status = todo.getStatus();
    }
}
