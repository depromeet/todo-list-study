package com.depromeet.todolist.controller;


import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class TodoListController {
    private final TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseTodoDto>> userTodoList(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.findAllTodo(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/todo/{todoId}") //todo REST 한가..?
    public ResponseEntity<ResponseTodoDto> userTodo(@PathVariable Long id, @PathVariable Long todoId) {
        return new ResponseEntity<>(todoService.findTodo(id, todoId), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void addUserTodo(@PathVariable Long id, @RequestBody RequestTodoDto requestTodoDto) {
        todoService.addTodo(id, requestTodoDto);
    }

    @PatchMapping("/{id}")
    public void updateUserTodo(@PathVariable Long id, @RequestBody RequestTodoDto requestTodoDto) {
        todoService.updateTodoTitle(id, requestTodoDto.getTitle());
    }

    @DeleteMapping("/{id}")
    public void deleteUserTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
