package com.depromeet.todolist.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name = "name", nullable = false)
    private String name;


//    @Embedded
//    private Todos todos = new Todos();


    public User(String name) {
        this.name = name;
    }
}
