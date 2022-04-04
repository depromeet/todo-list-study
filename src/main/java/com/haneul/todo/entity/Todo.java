package com.haneul.todo.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;

@Getter
@Entity
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public Todo(String title, String content, Status status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateStatus() {
        this.status = this.status.change();
    }
}
