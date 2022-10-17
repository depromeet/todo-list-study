package com.depromeet.todolist.service;

import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.BusinessException;
import com.depromeet.todolist.exception.ErrorCode;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final UserRepository userRepository;


    public User findUserByIdIfExists(String name){
        return userRepository.findById(name)
                .orElseThrow(() -> BusinessException.builder()
                        .errorCode(ErrorCode.NO_USER)
                        .build());
    }
}
