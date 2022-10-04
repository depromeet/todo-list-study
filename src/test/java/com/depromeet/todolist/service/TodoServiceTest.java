package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.TodoRepository;
import com.depromeet.todolist.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TodoServiceTest {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private TodoService todoService;
//    @Autowired
//    private TodoRepository todoRepository;
//
//    RequestUserDto userACreateDto;
//    RequestUserDto userBCreateDto;
//    RequestUserDto userADto;
//    RequestUserDto userBDto;
//
//    @BeforeEach
//    void saveUsers() {
//        userACreateDto = new RequestUserDto();
//        userBCreateDto = new RequestUserDto();
//        userACreateDto.setName("A");
//        userBCreateDto.setName("B");
//        User userA = userService.createUser(userACreateDto);
//        userADto = new RequestUserDto(userA.getName());
//        User userB = userService.createUser(userBCreateDto);
//        userBDto = new RequestUserDto(userB.getName());
//
//        todoService.addTodo(userADto.getId(), new RequestTodoDto("할 일 1"));
//        todoService.addTodo(userADto.getId(), new RequestTodoDto("할 일 2"));
//
//    }
//
//    @AfterEach
//    void cleanUsers() {
//        todoRepository.deleteAll();
//        userRepository.deleteAll();
//    }

//    @Test
//    void findAllTodo() {
//        List<ResponseTodoDto> allTodo = todoService.findAllTodo(userADto.getId());
//        Assertions.assertThat(allTodo.size()).isEqualTo(2);
//    }

//    @Test
//    void findTodo() {
//    }
//
//    @Test
//    void addTodo() {
//        Todo todo = todoService.addTodo(userADto.getId(), new RequestTodoDto("새 할 일"));
//        List<ResponseTodoDto> allTodo = todoService.findAllTodo(userADto.getId());
//        Assertions.assertThat(allTodo.size()).isEqualTo(3);
//    }
//
//    @Test
//    void updateTodoTitle() {
//    }
//
//    @Test
//    void deleteTodo() {
//    }
}