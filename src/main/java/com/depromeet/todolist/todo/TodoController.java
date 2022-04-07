package com.depromeet.todolist.todo;

import com.depromeet.todolist.common.dto.ApiResult;
import com.depromeet.todolist.todo.dto.TodoRequest;
import com.depromeet.todolist.todo.dto.TodoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ApiResult<List<TodoResponse>> findAll() {
        return ApiResult.OK(
                todoService.findAll()
                        .getTodoResponses());
    }

    @GetMapping("/{todoId}")
    public ApiResult<TodoResponse> findAll(@PathVariable(value = "todoId") Long todoId) {
        return ApiResult.OK(
                todoService.find(todoId));
    }

    @PostMapping("/{todoId}")
    public ApiResult<TodoResponse> save(@PathVariable(value = "todoId") Long todoId, @RequestBody TodoRequest todoRequest) {
        return ApiResult.OK(
                todoService.save(todoRequest));
    }

    // COMMENT: Service 레이어의 void 메서드에 대한 api 리턴 방법
    @PutMapping("/{todoId}")
    public ApiResult<Boolean> update(@PathVariable(value = "todoId") Long todoId, @RequestBody TodoRequest todoRequest) {
        todoService.updateById(todoId, todoRequest);
        return ApiResult.OK(true);
    }

    @DeleteMapping("/{todoId}")
    public ApiResult<Boolean> delete(@PathVariable(value = "todoId") Long todoId, @RequestBody TodoRequest todoRequest) {
        todoService.deleteById(todoId);
        return ApiResult.OK(true);
    }

}
