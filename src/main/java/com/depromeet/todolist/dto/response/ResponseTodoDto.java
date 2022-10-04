package com.depromeet.todolist.dto.response;

import lombok.*;


@Getter
@RequiredArgsConstructor
public class ResponseTodoDto {
    private final Long id;
    private final String title;
}
