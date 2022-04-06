package com.depromeet.todolist.todo;

public enum TodoType {
    STUDY("학습"),
    WORK("업무"),
    HOUSE("집안일"),
    ETC("기타");

    private String korean;

    TodoType(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
