package com.depromeet.todolist.entity;

import com.depromeet.todolist.exception.BusinessException;
import com.depromeet.todolist.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor
public class Todos {
    @OneToMany(mappedBy = "user")
    private List<Todo> todoList;


    public Todos(List<Todo> todoList){
        this.todoList = todoList;
    }


    public Todo checkUserContainsTodo(Long todoId) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(todoId))
                .findFirst()
                .orElseThrow(()-> BusinessException.builder()
                        .errorCode(ErrorCode.NO_TODO)
                        .errorDetail("해당 사용자에게 존재하지 않는 할 일")
                        .build());
    }
}
