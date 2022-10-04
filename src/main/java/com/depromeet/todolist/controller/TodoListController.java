package com.depromeet.todolist.controller;


import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.customException.TodoNotFoundException;
import com.depromeet.todolist.exception.customException.UserNotFoundException;
import com.depromeet.todolist.service.TodoService;
import com.depromeet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/{name}/todo-list")
@RequiredArgsConstructor
public class TodoListController {
    private final TodoService todoService;
    private final UserService userService;

    @GetMapping //todo ResponseTodoDto로 변환 방법 생각하기
    public ResponseEntity<List<ResponseTodoDto>> userTodoList(@PathVariable String name) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        List<Todo> allTodo = user.getTodoList().getTodoList();
//        List<Todo> allTodo = todoService.findAllTodo(name);
        List<ResponseTodoDto> allTodoDto = new ArrayList<>();
        for (Todo todo : allTodo) {
            allTodoDto.add(new ResponseTodoDto(todo.getId(), todo.getTitle()));
        }
        return new ResponseEntity<>(allTodoDto, HttpStatus.OK);
    }


    // 해당 유저의 todoList에 맞는 todo가 있는지 확인하는 로직 추가 작성하기
    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> userTodo(@PathVariable String name, @PathVariable Long todoId) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        Todo todo = todoService.findTodo(name, todoId);
        if (todo == null || !todoService.isUserContainsTodo(name, todoId)) {
            throw new TodoNotFoundException();
        }

        return new ResponseEntity<>(new ResponseTodoDto(todo.getId(), todo.getTitle()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseTodoDto> addUserTodo(@PathVariable String name, @RequestBody RequestTodoDto requestTodoDto) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        Todo todo = todoService.addTodo(name, requestTodoDto);
        return new ResponseEntity<>(new ResponseTodoDto(todo.getId(), todo.getTitle()), HttpStatus.CREATED);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> updateUserTodo(@PathVariable String name, @PathVariable Long todoId, @RequestBody RequestTodoDto requestTodoDto) {
        User user = userService.findUser(new RequestUserDto(name));
        if (user == null) throw new UserNotFoundException();
        Todo todo = todoService.findTodo(name, todoId);
        if (todo == null || !todoService.isUserContainsTodo(name, todoId)) {
            throw new TodoNotFoundException();
        }
        Todo updatedTodo = todoService.updateTodoTitle(todoId, requestTodoDto.getTitle());
        return new ResponseEntity<>(new ResponseTodoDto(updatedTodo.getId(), updatedTodo.getTitle()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ResponseTodoDto> deleteUserTodo(@PathVariable String name, @PathVariable Long todoId) {
        User user = userService.findUser(new RequestUserDto(name));
        if(user == null) throw new UserNotFoundException();
        Todo todo = todoService.findTodo(name, todoId);
        if (todo == null || !todoService.isUserContainsTodo(name, todoId)) {
            throw new TodoNotFoundException();
        }
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
