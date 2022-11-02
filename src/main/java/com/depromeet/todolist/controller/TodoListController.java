package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.TodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Todo Controller")
@Slf4j
@RestController
@RequestMapping("/api/v1/users/{userId}/todos")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoService todoService;

    @ApiOperation(value = "user 존재 확인, 성공 시 user 의 todo 목록 반환 (목록 조회)")
    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> userTodos(@PathVariable String userId) {
        List<TodoDto.Response> todosDtoResponse = todoService.getUserTodos(userId);
        return ResponseEntity.ok().body(todosDtoResponse);
    }

    @ApiOperation(value = "user 존재 확인, todo 존재 확인, 해당 user 의 todo 여부 확인, 성공 시 user 의 Todo 반환 (단건 조회)")
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> userTodo(@PathVariable String userId, @PathVariable Long todoId) {
        TodoDto.Response todoDtoResponse = todoService.getTodo(userId, todoId);
        return ResponseEntity.ok().body(todoDtoResponse);
    }

    @ApiOperation(value = "user 존재 확인, 성공 시 user 의 todo 생성 및 반환")
    @PostMapping
    public ResponseEntity<EntityModel<Todo>> addUserTodo(@PathVariable String userId, @RequestBody TodoDto.Request todoRequest) {
        EntityModel<Todo> todoEntityModel = todoService.addTodo(userId, todoRequest);
        return ResponseEntity.created(todoEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(todoEntityModel);
    }

    @ApiOperation(value = "user 존재 확인, todo 존재 확인, 해당 user 의 todo 여부 확인, 성공 시 user 의 todo 수정 및 반환")
    @PatchMapping("/{todoId}")
    public ResponseEntity<EntityModel<Todo>> updateUserTodo(@PathVariable String userId, @PathVariable Long todoId, @RequestBody TodoDto.Request todoRequest) {
        EntityModel<Todo> todoEntityModel = todoService.updateTodoTitle(userId, todoId, todoRequest.getTitle());
        return ResponseEntity.created(todoEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(todoEntityModel);
    }

    @ApiOperation(value = "user 존재 확인, todo 존재 확인, 해당 user 의 todo 여부 확인, 성공 시 user 의 todo 삭제")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDto.Response> deleteUserTodo(@PathVariable String userId, @PathVariable Long todoId) {
        todoService.deleteTodo(userId, todoId);
        return ResponseEntity.noContent().build();
    }
}
