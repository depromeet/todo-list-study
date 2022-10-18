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
    private String userName;


    public Todo(String title, String userName) {
        this.title = title;
        this.userName = userName;
    }


    public void updateTitle(String title) {
        this.title = title;
    }
}
