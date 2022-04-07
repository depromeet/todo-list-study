package com.depromeet.todolist.service;

import com.depromeet.todolist.model.TodoEntity;
import com.depromeet.todolist.model.TodoRequest;
import com.depromeet.todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

}
