package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("유저 생성 테스트")
    void createUser() { // 유저가 있어야하는 테스트 대비해서 가장 먼저 실행
        RequestUserDto user1 = new RequestUserDto();
        RequestUserDto user2 = new RequestUserDto();
        user1.setName("A");
        user2.setName("B");
        User userA = userService.createUser(user1);
        User userB = userService.createUser(user2);
        Assertions.assertThat(userA.getName()).isEqualTo("A");
        List<User> all = userRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void findUser() {

    }
//
//
//    @Test
//    void deleteUser() {
//    }
}