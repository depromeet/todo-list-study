package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.customException.DuplicatedUserException;
import com.depromeet.todolist.exception.customException.UserNotFoundException;
import com.depromeet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto requestUserDto) {
        User user = userService.findUser(requestUserDto);
        if(user != null){
            throw new DuplicatedUserException();
        }
        User createdUser = userService.createUser(requestUserDto);
        return new ResponseEntity<>(new ResponseUserDto(createdUser.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseUserDto> findUser(@PathVariable String name) {
        RequestUserDto requestUserDto = new RequestUserDto(name);
        User user = userService.findUser(requestUserDto);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(new ResponseUserDto(user.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ResponseUserDto> deleteUser(@PathVariable String name) {
        RequestUserDto requestUserDto = new RequestUserDto(name);
        User user = userService.findUser(requestUserDto);
        if (user == null) {
            throw new UserNotFoundException();
        }
        userService.deleteUser(requestUserDto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
