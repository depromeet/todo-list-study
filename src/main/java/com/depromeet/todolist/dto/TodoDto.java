package com.depromeet.todolist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@ApiModel(description = "이 모델은 TodoDto.")
public class TodoDto {

    @ApiModel(description = "이 모델은 TodoDto.Request")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @ApiModelProperty(required = true, value="할 일 id")
        private Long todoId;
        @ApiModelProperty(required = true, value="할 일 내용")
        private String title;
    }

    @ApiModel(description = "이 모델은 TodoDto.Response")
    @Getter
    @RequiredArgsConstructor
    public static class Response {
        @ApiModelProperty(required = true, value="할 일 id")
        private final Long id;
        @ApiModelProperty(required = true, value="할 일 id")
        private final String title;
    }

}
