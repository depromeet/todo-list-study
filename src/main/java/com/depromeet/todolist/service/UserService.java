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


    public ResponseUserDto findUser(String userId) {
        User user = commonService.findUserByIdIfExists(userId);
        return userEntityToDto(user);
    }


    public void deleteUser(String userId) {
        User user = commonService.findUserByIdIfExists(userId);
        userRepository.delete(user);
    }


    private ResponseUserDto userEntityToDto(User user) {
        return new ResponseUserDto(user.getUserId(), user.getName());
    }


    private User userDtoToEntity(RequestUserDto requestUserDto) {
        return new User(requestUserDto.getUserId(), requestUserDto.getName());
    }


    private User checkUserDuplicated(RequestUserDto requestUserDto) {
        String userId = requestUserDto.getUserId();
        if (userRepository.existsById(userId)) {
            throw BusinessException.builder()
                    .errorCode(ErrorCode.DUPLICATED_USER)
                    .errorDetail("사용자 중복 아이디 : " + userId)
                    .build();
        }
        return userDtoToEntity(requestUserDto);
    }
}
