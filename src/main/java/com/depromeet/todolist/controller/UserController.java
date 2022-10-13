package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.ErrorCode;
import com.depromeet.todolist.exception.BusinessException;
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
        ResponseUserDto responseUserDto = userService.createUser(requestUserDto);
        return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseUserDto> findUser(@PathVariable String name) {
        ResponseUserDto responseUserDto = userService.findUser(name);
        return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ResponseUserDto> deleteUser(@PathVariable String name) {

        userService.deleteUser(name);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
