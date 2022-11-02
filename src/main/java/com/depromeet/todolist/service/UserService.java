package com.depromeet.todolist.service;

import com.depromeet.todolist.converter.UserEntityConverter;
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

    private final UserRepository userRepository;
    private final UserEntityConverter userEntityConverter;

    public UserDto.Response createUser(UserDto.Request userRequest) {
        User notDuplicatedUser = checkUserDuplicated(userRequest);
        User savedUser = userRepository.save(notDuplicatedUser);
        return userEntityConverter.userEntityToDto(savedUser);
    }

    public UserDto.Response findUser(String userId) {
        User getUser = findUserByIdIfExists(userId);
        return userEntityConverter.userEntityToDto(getUser);
    }

    public void deleteUser(String userId) {
        User user = findUserByIdIfExists(userId);
        userRepository.delete(user);

    }

    public User findUserByIdIfExists(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.builder()
                        .errorCode(ErrorCode.NO_USER)
                        .errorDetail("존재하지 않는 사용자")
                        .build());
    }

    private User checkUserDuplicated(UserDto.Request userRequest) {
        String userId = userRequest.getUserId();
        if (userRepository.existsById(userId)) {
            throw BusinessException.builder()
                    .errorCode(ErrorCode.DUPLICATED_USER)
                    .errorDetail("사용자 중복 아이디 : " + userId)
                    .build();
        }
        return userEntityConverter.userDtoToEntity(userRequest);
    }
}
