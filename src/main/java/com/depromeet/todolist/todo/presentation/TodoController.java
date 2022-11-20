package com.depromeet.todolist.todo.presentation;

import java.util.UUID;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Todo-list controller", description = "todo-list 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/todos")
public class TodoController {
    private final TodoFacade todoFacade;

    @Operation(summary = "투두 목록 하나를 생성합니다.")
    @PostMapping
    public ResponseEntity<TodoItemResponse> create(@RequestBody TodoItemCreateRequest request) {
        var response = todoFacade.createItem(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "투두 목록 하나를 가져옵니다.")
    @GetMapping("{id}")
    public ResponseEntity<TodoItemResponse> findOne(@PathVariable UUID id) {
        var response = todoFacade.getItem(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "투두 목록 모두를 가져옵니다.")
    @GetMapping
    public ResponseEntity<TodoListResponse> findAll() {
        var response = todoFacade.getList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "투두 목록 하나를 업데이트합니다.")
    @PutMapping("{id}")
    public ResponseEntity<TodoItemResponse> update(@PathVariable UUID id, @RequestBody TodoItemUpdateRequest request) {
        var response = todoFacade.updateItem(id, request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "투두 목록 하나를 제거합니다.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        todoFacade.deleteItem(id);

        return ResponseEntity.noContent().build();
    }
}
