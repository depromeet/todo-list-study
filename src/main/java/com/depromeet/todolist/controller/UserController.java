package com.depromeet.todolist.controller;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.customExcption.DuplicatedUserExcption;
import com.depromeet.todolist.exception.customExcption.UserNotFoundException;
import com.depromeet.todolist.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto requestUserDto) throws DuplicatedUserExcption {
        User user = userService.createUser(requestUserDto);
        if(user == null){
            throw new DuplicatedUserExcption();
        }
        return new ResponseEntity<>(new ResponseUserDto(user.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseUserDto> findUser(@RequestBody RequestUserDto requestUserDto) throws UserNotFoundException {
        User user = userService.findUser(requestUserDto);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new ResponseEntity<>(new ResponseUserDto(user.getName()), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseUserDto> deleteUser(@RequestBody RequestUserDto requestUserDto) throws UserNotFoundException {
        User user = userService.findUser(requestUserDto);
        if (user == null) {
            throw new UserNotFoundException();
        }
        userService.deleteUser(requestUserDto);
        return new ResponseEntity<>(new ResponseUserDto(), HttpStatus.NO_CONTENT);
    }
}
