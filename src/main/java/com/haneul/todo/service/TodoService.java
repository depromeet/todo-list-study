package com.haneul.todo.service;

import com.haneul.todo.dto.request.TodoRequest;
import com.haneul.todo.dto.response.TodoDeleteResponse;
import com.haneul.todo.dto.response.TodoListResponse;
import com.haneul.todo.dto.response.TodoResponse;
import com.haneul.todo.entity.Todo;
import com.haneul.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo instance = new Todo(todoRequest.title(), todoRequest.content());
        Todo todo = todoRepository.save(instance);
        return new TodoResponse(todo);
    }

    public TodoResponse findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow();
        return new TodoResponse(todo);
    }

    public TodoListResponse findAll() throws IllegalAccessException {
        List<Todo> todoList = todoRepository.findAll();
        if (todoList.isEmpty()) throw new IllegalAccessException();

        List<TodoResponse> todoResponseList = new ArrayList<>();
        for (Todo todo : todoList)
            todoResponseList.add(new TodoResponse(todo));
        return new TodoListResponse(todoResponseList);
    }

    public TodoResponse update(TodoRequest todoRequest, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow();
        todo.update(todoRequest.title(), todoRequest.content());
        return new TodoResponse(todo);
    }

    public TodoResponse updateStatus(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow();
        todo.updateStatus();
        return new TodoResponse(todo);
    }

    public TodoDeleteResponse deleteById(Long id) {
        todoRepository.deleteById(id);
        return new TodoDeleteResponse(id);
    }

}
