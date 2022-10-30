package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.UserDto;
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

    public UserDto.Response createUser(UserDto.Request userRequest) {
        User user = checkUserDuplicated(userRequest);
        User savedUser = userRepository.save(user);
        return userEntityToDto(savedUser);
    }

    public UserDto.Response findUser(String userId) {
        User user = commonService.findUserByIdIfExists(userId);
        return userEntityToDto(user);
    }

    public void deleteUser(String userId) {
        User user = commonService.findUserByIdIfExists(userId);
        userRepository.delete(user);
    }

    private UserDto.Response userEntityToDto(User user) {
        return new UserDto.Response(user.getUserId(), user.getName());
    }

    private User userDtoToEntity(UserDto.Request userRequest) {
        return new User(userRequest.getUserId(), userRequest.getName());
    }

    private User checkUserDuplicated(UserDto.Request userRequest) {
        String userId = userRequest.getUserId();
        if (userRepository.existsById(userId)) {
            throw BusinessException.builder()
                    .errorCode(ErrorCode.DUPLICATED_USER)
                    .errorDetail("사용자 중복 아이디 : " + userId)
                    .build();
        }
        return userDtoToEntity(userRequest);
    }
}
