package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestUserDto;
import com.depromeet.todolist.dto.response.ResponseUserDto;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.BusinessException;
import com.depromeet.todolist.exception.ErrorCode;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final CommonService commonService;
    private final UserRepository userRepository;


    public ResponseUserDto createUser(RequestUserDto requestUserDto) {
        User user = checkUserDuplicated(requestUserDto);
        User savedUser = userRepository.save(user);
        return userEntityToDto(savedUser);
    }


    public ResponseUserDto findUser(String name) {
        User user = commonService.findUserByIdIfExists(name);
        return userEntityToDto(user);
    }


    public void deleteUser(String name) {
        User user = commonService.findUserByIdIfExists(name);
        userRepository.delete(user);
    }


    private ResponseUserDto userEntityToDto(User user) {
        return new ResponseUserDto(user.getName());
    }


    private User checkUserDuplicated(RequestUserDto requestUserDto) {
        String userName = requestUserDto.getName();
        if (userRepository.existsById(userName)) {
            throw BusinessException.builder()
                    .errorCode(ErrorCode.DUPLICATED_USER)
                    .errorDetail(userName+" 사용자 중복됨")
                    .build();
        }
        return new User(userName);
    }
}
