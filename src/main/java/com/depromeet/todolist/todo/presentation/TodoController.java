package com.depromeet.todolist.todo.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.todolist.todo.dto.request.TodoItemCreateRequest;
import com.depromeet.todolist.todo.dto.request.TodoItemUpdateRequest;
import com.depromeet.todolist.todo.dto.response.TodoItemResponse;
import com.depromeet.todolist.todo.dto.response.TodoListResponse;
import com.depromeet.todolist.todo.facade.TodoFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/todos")
public class TodoController {
    private final TodoFacade todoFacade;

    @PostMapping
    public ResponseEntity<TodoItemResponse> create(@RequestBody TodoItemCreateRequest request) {
        var response = todoFacade.createItem(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoItemResponse> findOne(@PathVariable UUID id) {
        var response = todoFacade.getItem(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<TodoListResponse> findAll() {
        var response = todoFacade.getList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoItemResponse> update(@PathVariable UUID id, @RequestBody TodoItemUpdateRequest request) {
        var response = todoFacade.updateItem(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        todoFacade.deleteItem(id);

        return ResponseEntity.noContent().build();
    }
}
