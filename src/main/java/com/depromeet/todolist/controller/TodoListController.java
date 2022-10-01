package com.depromeet.todolist.controller;


import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
//@ResponseBody
@RequestMapping("/user")
@RequiredArgsConstructor
public class TodoListController {
    private final TodoService todoService;
    //    생성, 수정, 삭제, 단건조회, 전체조회

    //  사용자 할 일 목록 조회
    @GetMapping("/{id}")
    public List<ResponseTodoDto> userTodoList(@PathVariable Long id) { // 전체 투두 목록 조회
        log.info("userTodoList 목록 조회");
        return todoService.findAllTodo(id);
    }

    //  사용자 할 일 조회
    @GetMapping("/{id}/todo/{todoId}") //todo REST 한가..?
    public ResponseTodoDto userTodo(@PathVariable Long id, @PathVariable Long todoId) {
        return todoService.findTodo(id, todoId);
    }

    //  사용자 할 일 추가
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public void addUserTodo(@PathVariable Long id, @RequestBody RequestTodoDto requestTodoDto) {
        todoService.addTodo(id, requestTodoDto);
    }

    //  사용자 할 일 수정
    @PatchMapping("/{id}")
    public void updateUserTodo(@PathVariable Long id, @RequestBody RequestTodoDto requestTodoDto) {
        todoService.updateTodoTitle(id, requestTodoDto.getTitle());
    }

    //  사용자 할 일 삭제
    @DeleteMapping("/{id}")
    public void deleteUserTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
