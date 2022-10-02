package com.depromeet.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseUserDto {
    private Long id;
    private String name;
}
