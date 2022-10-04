package com.depromeet.todolist.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
//    private List<Todo> todoList = new ArrayList<>();

    @Embedded
    private TodoList todoList;

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public User(){

    }

    public User(String name) {
        this.name = name;
    }
}
