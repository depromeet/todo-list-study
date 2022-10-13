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
        User userCheck = userRepository.findById(requestUserDto.getName()).orElse(null);
        if(userCheck != null) throw new BusinessException(ErrorCode.DUPLICATED_USER);
        User savedUser = userRepository.save(new User(requestUserDto.getName()));
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
}
