package com.depromeet.todolist.controller;

import com.depromeet.todolist.converter.TodoEntityConverter;
import com.depromeet.todolist.dto.TodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users/{userId}/todos")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> userTodos(@PathVariable String userId) {
        List<TodoDto.Response> todosDtoResponse = todoService.getUserTodos(userId);
        return ResponseEntity.ok().body(todosDtoResponse);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> userTodo(@PathVariable String userId, @PathVariable Long todoId) {
        TodoDto.Response todoDtoResponse = todoService.getTodo(userId, todoId);
        return ResponseEntity.ok().body(todoDtoResponse);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Todo>> addUserTodo(@PathVariable String userId, @RequestBody TodoDto.Request todoRequest) {
        EntityModel<Todo> todoEntityModel = todoService.addTodo(userId, todoRequest);
        return ResponseEntity.created(todoEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(todoEntityModel);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<EntityModel<Todo>> updateUserTodo(@PathVariable String userId, @PathVariable Long todoId, @RequestBody TodoDto.Request todoRequest) {
        EntityModel<Todo> todoEntityModel = todoService.updateTodoTitle(userId, todoId, todoRequest.getTitle());
        return ResponseEntity.created(todoEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(todoEntityModel);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> deleteUserTodo(@PathVariable String userId, @PathVariable Long todoId) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.noContent().build();
    }
}
