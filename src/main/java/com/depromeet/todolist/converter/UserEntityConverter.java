package com.depromeet.todolist.converter;

import com.depromeet.todolist.dto.UserDto;
import com.depromeet.todolist.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {

    public UserDto.Response userEntityToDto(User user) {
        return new UserDto.Response(user.getUserId(), user.getName());
    }

    public User userDtoToEntity(UserDto.Request userRequest) {
        return new User(userRequest.getUserId(), userRequest.getName());
    }
}
