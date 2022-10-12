package com.depromeet.todolist.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.engine.FetchStyle;

import javax.persistence.*;

@Getter
@Entity
public class User {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Todos todos;

    public User(){
    }

    @Builder
    public User(String name, Todos todos) {
        this.name = name;
        this.todos = todos;
    }
}
