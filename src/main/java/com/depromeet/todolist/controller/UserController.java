package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseUserDto;
import com.depromeet.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = userService.createUser(requestUserDto);
        return ResponseEntity.created(URI.create("/api/v1/users")).body(responseUserDto);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> findUser(@PathVariable String userId) {
        ResponseUserDto responseUserDto = userService.findUser(userId);
        return ResponseEntity.ok().body(responseUserDto);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
