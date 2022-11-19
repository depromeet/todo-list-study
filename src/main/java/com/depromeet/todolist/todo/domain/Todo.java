package com.depromeet.todolist.todo.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.depromeet.todolist.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String content;

    private boolean finished;

    @Builder
    public Todo(String content, boolean finished) {
        this.content = content;
        this.finished = finished;
    }

    public Todo updateContent(String content) {
        this.content = content;
        return this;
    }

    public Todo updateFinished(boolean finished) {
        this.finished = finished;
        return this;
    }
}
