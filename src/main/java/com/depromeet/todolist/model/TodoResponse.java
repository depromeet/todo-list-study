package com.depromeet.todolist.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;

    public TodoResponse(TodoEntity todoEntity) {
        this.id=todoEntity.getId();
        this.title=todoEntity.getTitle();
        this.order=todoEntity.getOrder();
        this.completed=todoEntity.getCompleted();
    }
}
