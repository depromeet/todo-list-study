package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.TodoRequest;
import com.depromeet.todolist.dto.TodoResponse;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        List<Todo> list = todoService.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        Todo result = todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.addItem(todoRequest);
        return ResponseEntity.ok(new TodoResponse(todo));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.updateById(id, todoRequest);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
