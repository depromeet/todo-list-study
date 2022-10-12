package com.depromeet.todolist.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.engine.FetchStyle;

import javax.persistence.*;

@Getter
@Entity
public class User {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Todos todos = new Todos();

    public User(){
    }

    public User(String name) {
        this.name = name;
    }
}
