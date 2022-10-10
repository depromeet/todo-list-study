package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTest {

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
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
//        userADto = new RequestUserDto(userA.getId(), userA.getName());
//        User userB = userService.createUser(userBCreateDto);
//        userBDto = new RequestUserDto(userB.getId(), userB.getName());
//    }
//
//    @AfterEach
//    void cleanUsers() {
//        userRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("유저 생성 테스트")
//    void createUser() { // 유저가 있어야하는 테스트 대비해서 가장 먼저 실행
//        List<User> all = userRepository.findAll();
//        Assertions.assertThat(all.size()).isEqualTo(2);
//    }
//
//    @Test
//    void findUser() {
//        User a = userService.findUser(userADto);
//        Assertions.assertThat(a.getName()).isEqualTo("A");
//    }
//
//
//    @Test
//    void deleteUser() {
//        userService.deleteUser(userBDto);
//        List<User> all = userRepository.findAll();
//        Assertions.assertThat(all.size()).isEqualTo(1);
//    }
}
