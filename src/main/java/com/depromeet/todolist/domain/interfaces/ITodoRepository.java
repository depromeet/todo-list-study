package com.depromeet.todolist.domain.interfaces;

import com.depromeet.todolist.infrastructure.mysql.TodoEntity;

import java.util.List;
import java.util.Optional;

public interface ITodoRepository {

    List<TodoEntity> findAll();
    Optional<TodoEntity> findById(long id);
    TodoEntity save(TodoEntity todoItem);

    int delete(long id);
}
