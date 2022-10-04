package com.depromeet.todolist.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class TodoList {
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Todo> todoList = new ArrayList<>();

    public TodoList(){}

    public TodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
