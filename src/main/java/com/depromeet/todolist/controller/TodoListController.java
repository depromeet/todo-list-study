package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
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
    public ResponseEntity<List<ResponseTodoDto>> userTodos(@PathVariable String userId) {
        List<ResponseTodoDto> responseTodos = todoService.getUserTodoList(userId);
        return ResponseEntity.ok().body(responseTodos);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> userTodo(@PathVariable String userId, @PathVariable Long todoId) {
        ResponseTodoDto responseTodoDto = todoService.getTodo(userId, todoId);
        return ResponseEntity.ok().body(responseTodoDto);
    }

    @PostMapping
    public ResponseEntity<ResponseTodoDto> addUserTodo(@PathVariable String userId, @RequestBody RequestTodoDto requestTodoDto) {
        ResponseTodoDto responseTodoDto = todoService.addTodo(userId, requestTodoDto);
        return ResponseEntity.created(URI.create("/api/v1/users/"+ userId +"/todos")).body(responseTodoDto);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> updateUserTodo(@PathVariable String userId, @PathVariable Long todoId, @RequestBody RequestTodoDto requestTodoDto) {
        ResponseTodoDto responseTodoDto = todoService.updateTodoTitle(userId, todoId, requestTodoDto.getTitle());
        return ResponseEntity.created(URI.create("/api/v1/users/"+ userId +"/todos/" + todoId)).body(responseTodoDto);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> deleteUserTodo(@PathVariable String userId, @PathVariable Long todoId) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.noContent().build();
    }
}
