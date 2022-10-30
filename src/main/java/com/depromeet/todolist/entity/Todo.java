package com.depromeet.todolist.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "user_id", nullable = false)
    private String userId;

    public Todo(String title, String userId) {
        this.title = title;
        this.userId = userId;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
