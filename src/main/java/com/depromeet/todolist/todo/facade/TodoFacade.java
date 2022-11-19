package com.depromeet.todolist.todo.facade;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.todolist.todo.application.TodoService;
import com.depromeet.todolist.todo.dto.request.TodoItemCreateRequest;
import com.depromeet.todolist.todo.dto.request.TodoItemUpdateRequest;
import com.depromeet.todolist.todo.dto.response.TodoItemResponse;
import com.depromeet.todolist.todo.dto.response.TodoListResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TodoFacade {
    private final TodoService todoService;

    @Transactional
    public TodoItemResponse createItem(TodoItemCreateRequest request) {
        var response = todoService.createItem(request.content(), request.finished());

        return new TodoItemResponse(response);
    }

    @Transactional(readOnly = true)
    public TodoListResponse getList() {
        var response = todoService.getList()
                .stream()
                .map(TodoListResponse.TodoResponse::new)
                .collect(Collectors.toList());

        return new TodoListResponse(response);
    }

    @Transactional(readOnly = true)
    public TodoItemResponse getItem(UUID id) {
        var response = todoService.getItem(id);

        return new TodoItemResponse(response);
    }

    @Transactional
    public TodoItemResponse updateItem(UUID id, TodoItemUpdateRequest request) {
        var response = todoService.updateItem(id, request.content(), request.finished());

        return new TodoItemResponse(response);
    }

    @Transactional
    public void deleteItem(UUID id) {
        todoService.deleteItem(id);
    }
}
