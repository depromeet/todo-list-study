package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.UserDto;
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
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.Request userRequest) {
        UserDto.Response userResponse = userService.createUser(userRequest);
        return ResponseEntity.created(URI.create("/api/v1/users")).body(userResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto.Response> findUser(@PathVariable String userId) {
        UserDto.Response userResponse = userService.findUser(userId);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto.Response> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
