package com.depromeet.todolist.dto;

import com.depromeet.todolist.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;

    public TodoResponse(Todo todo) {
        this.id=todo.getId();
        this.title=todo.getTitle();
        this.order=todo.getOrder();
        this.completed=todo.getCompleted();
    }
}
