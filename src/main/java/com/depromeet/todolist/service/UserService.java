package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //유저 단일 조회
    public User findUser(RequestUserDto requestUserDto) {
        return userRepository.findById(requestUserDto.getId()).orElse(null);
    }

    //유저 생성
    public User createUser(RequestUserDto requestUserDto) {
//        User exists = findUser(requestUserDto);
//        if (exists == null) { // 존재하는 유저가 없다면 새로 생성
            User user = new User();
            user.setName(requestUserDto.getName());
            return userRepository.save(user);
//        } else {
//            return null;
//        }
    }

    //유저 삭제
    public void deleteUser(RequestUserDto requestUserDto) {
        User user = findUser(requestUserDto);
        if (user != null) {
            userRepository.delete(user);
        } else {
            log.info("삭제할 유저가 존재하지 않습니다.");
        }
    }
}
