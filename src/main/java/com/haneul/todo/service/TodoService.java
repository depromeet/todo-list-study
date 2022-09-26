package com.haneul.todo.service;

import com.haneul.todo.dto.request.TodoRequest;
import com.haneul.todo.dto.response.TodoDeleteResponse;
import com.haneul.todo.dto.response.TodoListResponse;
import com.haneul.todo.dto.response.TodoResponse;
import com.haneul.todo.entity.Status;
import com.haneul.todo.entity.Todo;
import com.haneul.todo.exception.ErrorCode;
import com.haneul.todo.exception.TodoNotExistException;
import com.haneul.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo instance = new Todo(todoRequest.title(), todoRequest.content(), Status.PROCEEDING);
        Todo todo = todoRepository.save(instance);
        return new TodoResponse(todo);
    }

    @Transactional(readOnly = true)
    public TodoResponse findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotExistException(ErrorCode.NOT_TODO_FOUND));
        return new TodoResponse(todo);
    }

    @Transactional(readOnly = true)
    public TodoListResponse findAll() {
        List<Todo> todos = todoRepository.findAll();

        List<TodoResponse> todoResponses = new ArrayList<>();
        todos.forEach(todo ->
                todoResponses.add(new TodoResponse(todo))
        );
        return new TodoListResponse(todoResponses);
    }

    @Transactional
    public TodoResponse update(TodoRequest todoRequest, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotExistException(ErrorCode.NOT_TODO_FOUND));
        todo.update(todoRequest.title(), todoRequest.content());
        return new TodoResponse(todo);
    }

    @Transactional
    public TodoResponse updateStatus(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotExistException(ErrorCode.NOT_TODO_FOUND));
        todo.updateStatus();
        return new TodoResponse(todo);
    }

    @Transactional
    public TodoDeleteResponse deleteById(Long id) {
        todoRepository.deleteById(id);
        return new TodoDeleteResponse(id);
    }

}
