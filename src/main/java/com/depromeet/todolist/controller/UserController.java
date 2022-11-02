package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.UserDto;
import com.depromeet.todolist.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Api(value = "User Controller")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "존재하는 userId인지 확인, 없을 시 user 생성 및 반환")
    @PostMapping
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.Request userRequest) {
        UserDto.Response userResponse = userService.createUser(userRequest);
        return ResponseEntity.created(URI.create("/api/v1/users")).body(userResponse);
    }

    @ApiOperation(value = "user 존재 확인, 성공 시 user 반환")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto.Response> findUser(@PathVariable String userId) {
        UserDto.Response userResponse = userService.findUser(userId);
        return ResponseEntity.ok().body(userResponse);
    }

    @ApiOperation(value = "user 존재 확인, 성공 시 user 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto.Response> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
