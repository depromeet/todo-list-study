package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    //유저 생성
    @PostMapping("")
    public ResponseUserDto createUser(@RequestBody RequestUserDto requestUserDto) {
        User user = userService.createUser(requestUserDto);
        return new ResponseUserDto(user.getId(), user.getName());
    }

    //유저 단일 조회
    @GetMapping("")
    public ResponseUserDto findUser(@RequestBody RequestUserDto requestUserDto) {
        User user = userService.findUser(requestUserDto);
        return new ResponseUserDto(user.getId(), user.getName());
    }

    //유저 삭제
    @DeleteMapping("")
    public void deleteUser(@RequestBody RequestUserDto requestUserDto) {
        userService.deleteUser(requestUserDto);
    }
}
