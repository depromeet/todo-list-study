package com.depromeet.todolist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(description = "이 모델은 UserDto.")
public class UserDto {

    @ApiModel(description = "이 모델은 UserDto.Request")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @ApiModelProperty(required = true, value="사용자 id")
        private String userId;
        @ApiModelProperty(required = true, value="사용자 이름")
        private String name;
    }

    @ApiModel(description = "이 모델은 UserDto.Request")
    @Getter
    @RequiredArgsConstructor
    public static class Response {
        @ApiModelProperty(required = true, value="사용자 id")
        private final String userId;
        @ApiModelProperty(required = true, value="사용자 이름")
        private final String name;
    }

}
