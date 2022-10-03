package com.depromeet.todolist.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Todo> todoList = new ArrayList<>();
}
