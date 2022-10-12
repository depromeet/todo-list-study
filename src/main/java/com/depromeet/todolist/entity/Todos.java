package com.depromeet.todolist.entity;

import com.depromeet.todolist.exception.customException.TodoNotFoundException;
import com.depromeet.todolist.repository.TodoRepository;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Todos {

    @OneToMany(mappedBy = "user")
    private List<Todo> todoList;

    public Todos(List<Todo> todoList){
        this.todoList = todoList;
    }

    public Todos() {
    }


    public Todo checkUserContainsTodo(Long todoId) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(todoId))
                .findFirst()
                .orElseThrow(TodoNotFoundException::new);
    }
}
