package com.depromeet.todolist.todo;
import com.depromeet.todolist.common.BaseEntity;
import com.depromeet.todolist.todo.dto.TodoRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "todo_content")
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "todo_type")
    private TodoType type;

    @Column(name = "todo_delete_flag")
    private Boolean deleteFlag;

    private Todo(String content, TodoType type) {
        this.content = content;
        this.type = type;
    }

    void update(TodoRequest request) {
        this.content = request.getContent();
        this.type = request.getType();
    }

    void delete() {
        this.deleteFlag = true;
    }

    static public Todo of(String content, TodoType type) {
        return new Todo(content, type);
    }
}
