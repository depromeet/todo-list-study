package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return new ResponseEntity<>(userTodoList, HttpStatus.OK);
    }


    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> userTodo(@PathVariable String name, @PathVariable Long todoId) {
        ResponseTodoDto responseTodoDto = todoService.getTodo(name, todoId);
        return new ResponseEntity<>(responseTodoDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseTodoDto> addUserTodo(@PathVariable String name, @RequestBody RequestTodoDto requestTodoDto) {
        ResponseTodoDto responseTodoDto = todoService.addTodo(name, requestTodoDto);
        return new ResponseEntity<>(responseTodoDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> updateUserTodo(@PathVariable String name, @PathVariable Long todoId, @RequestBody RequestTodoDto requestTodoDto) {
        ResponseTodoDto responseTodoDto = todoService.updateTodoTitle(name, todoId, requestTodoDto.getTitle());
        return new ResponseEntity<>(responseTodoDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> deleteUserTodo(@PathVariable String name, @PathVariable Long todoId) {
        todoService.deleteTodo(name, todoId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}