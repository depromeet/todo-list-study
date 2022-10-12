package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.entity.Todos;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User findUser(RequestUserDto requestUserDto) {
        return userRepository.findById(requestUserDto.getName()).orElse(null);
    }

    public User createUser(RequestUserDto requestUserDto) {
        User user = new User(requestUserDto.getName());
        return userRepository.save(user);
    }

    public void deleteUser(RequestUserDto requestUserDto) {
        User user = findUser(requestUserDto);
        if (user != null) {
            userRepository.delete(user);
        } else {
            log.info("삭제할 유저가 존재하지 않습니다.");
        }
    }
}
