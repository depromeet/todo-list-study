package com.depromeet.todolist.error.exception.todo;

public class NotExistsTodoException extends RuntimeException {
    public NotExistsTodoException() {
        super("존재하지 않는 todo 입니다.");
    }
}