package com.haneul.todo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum Status {
    PROCEEDING("진행 중"),
    COMPLETED("완료");

    private final String title;

    public Status change() {
        if (this == PROCEEDING) return COMPLETED;
        else return PROCEEDING;
    }

}
