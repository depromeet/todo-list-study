package com.depromeet.todolist.entity;

import com.depromeet.todolist.exception.customException.TodoNotFoundException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Todos {
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Todo> todoList = new ArrayList<>();

    public Todos() {
    }

    public Todos(List<Todo> todoList){
        this.todoList = todoList;
    }

    public Todo checkUserContainsTodo(Long todoId) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(todoId))
                .findFirst()
                .orElseThrow(TodoNotFoundException::new);
    }
}
