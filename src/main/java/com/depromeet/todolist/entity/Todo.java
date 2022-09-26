package com.depromeet.todolist.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Todo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "todo_order", nullable = false)
    private Long order;

    @Column(nullable = false)
    private Boolean completed;

    public void updateTodo(String title, Long order, Boolean completed) {
        this.title = title;
        this.order = order;
        this.completed = completed;
    }
}
