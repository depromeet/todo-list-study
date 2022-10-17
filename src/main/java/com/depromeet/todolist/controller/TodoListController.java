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
@RequestMapping("/api/v1/users/{name}/todos")
@RequiredArgsConstructor
public class TodoListController {
    private final TodoService todoService;


    @GetMapping
    public ResponseEntity<List<ResponseTodoDto>> userTodoList(@PathVariable String name) {
        List<ResponseTodoDto> userTodoList = todoService.getUserTodoList(name);
        return ResponseEntity.ok().body(userTodoList);
    }


    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> userTodo(@PathVariable String name, @PathVariable Long todoId) {
        ResponseTodoDto responseTodoDto = todoService.getTodo(name, todoId);
        return ResponseEntity.ok().body(responseTodoDto);
    }


    @PostMapping
    public ResponseEntity<ResponseTodoDto> addUserTodo(@PathVariable String name, @RequestBody RequestTodoDto requestTodoDto) {
        ResponseTodoDto responseTodoDto = todoService.addTodo(name, requestTodoDto);
        return ResponseEntity.created(URI.create("/api/v1/users/"+ name +"/todos")).body(responseTodoDto);
    }


    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> updateUserTodo(@PathVariable String name, @PathVariable Long todoId, @RequestBody RequestTodoDto requestTodoDto) {
        ResponseTodoDto responseTodoDto = todoService.updateTodoTitle(name, todoId, requestTodoDto.getTitle());
        return ResponseEntity.created(URI.create("/api/v1/users/"+ name +"/todos/" + todoId)).body(responseTodoDto);
    }


    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> deleteUserTodo(@PathVariable String name, @PathVariable Long todoId) {
        todoService.deleteTodo(name, todoId);
        return ResponseEntity.noContent().build();
    }
}
