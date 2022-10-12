package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.customException.UserNotFoundException;
import com.depromeet.todolist.service.TodoService;
import com.depromeet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{name}/todo-list")
@RequiredArgsConstructor
@Transactional
public class TodoListController {
    private final TodoService todoService;
    private final UserService userService;


    @GetMapping //todo ResponseTodoDto로 변환 방법 생각하기

    public ResponseEntity<List<ResponseTodoDto>> userTodoList(@PathVariable String name) {
        User user = userService.findUser(new RequestUserDto(name));
        log.info("findUser");
        if(user == null) throw new UserNotFoundException();
        log.info("startAllTodos");
        List<Todo> allTodo = user.getTodos().getTodoList();
        log.info("endAllTodos");
        List<ResponseTodoDto> allTodoDto = new ArrayList<>();

        for (Todo todo : allTodo) {
            allTodoDto.add(new ResponseTodoDto(todo.getId(), todo.getTitle()));
        }
        return new ResponseEntity<>(allTodoDto, HttpStatus.OK);
    }


    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> userTodo(@PathVariable String name, @PathVariable Long todoId) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        Todo todo = user.getTodos().checkUserContainsTodo(todoId);
        return new ResponseEntity<>(new ResponseTodoDto(todo.getId(), todo.getTitle()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseTodoDto> addUserTodo(@PathVariable String name, @RequestBody RequestTodoDto requestTodoDto) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        Todo todo = todoService.addTodo(user, requestTodoDto);
        return new ResponseEntity<>(new ResponseTodoDto(todo.getId(), todo.getTitle()), HttpStatus.CREATED);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> updateUserTodo(@PathVariable String name, @PathVariable Long todoId, @RequestBody RequestTodoDto requestTodoDto) {
        User user = userService.findUser(new RequestUserDto(name));
        if (user == null) throw new UserNotFoundException();
        user.getTodos().checkUserContainsTodo(todoId);
        Todo updatedTodo = todoService.updateTodoTitle(todoId, requestTodoDto.getTitle());
        return new ResponseEntity<>(new ResponseTodoDto(updatedTodo.getId(), updatedTodo.getTitle()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> deleteUserTodo(@PathVariable String name, @PathVariable Long todoId) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        user.getTodos().checkUserContainsTodo(todoId);
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
