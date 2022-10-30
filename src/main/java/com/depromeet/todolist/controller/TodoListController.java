package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.TodoDto;
import com.depromeet.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users/{userId}/todos")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> userTodos(@PathVariable String userId) {
        List<TodoDto.Response> todosResponse = todoService.getUserTodos(userId);
        return ResponseEntity.ok().body(todosResponse);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> userTodo(@PathVariable String userId, @PathVariable Long todoId) {
        TodoDto.Response todoResponse = todoService.getTodo(userId, todoId);
        return ResponseEntity.ok().body(todoResponse);
    }

    @PostMapping
    public ResponseEntity<TodoDto.Response> addUserTodo(@PathVariable String userId, @RequestBody TodoDto.Request todoRequest) {
        TodoDto.Response todoResponse = todoService.addTodo(userId, todoRequest);
        return ResponseEntity.created(URI.create("/api/v1/users/"+ userId +"/todos")).body(todoResponse);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> updateUserTodo(@PathVariable String userId, @PathVariable Long todoId, @RequestBody TodoDto.Request todoRequest) {
        TodoDto.Response todoResponse = todoService.updateTodoTitle(userId, todoId, todoRequest.getTitle());
        return ResponseEntity.created(URI.create("/api/v1/users/"+ userId +"/todos/" + todoId)).body(todoResponse);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> deleteUserTodo(@PathVariable String userId, @PathVariable Long todoId) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.noContent().build();
    }
}
