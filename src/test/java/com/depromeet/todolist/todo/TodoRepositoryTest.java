package com.depromeet.todolist.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = {"local", "localdb"})
@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void todo_등록및조회() {
        Todo todoA = Todo.of("빨래", TodoType.HOUSE);
        todoRepository.save(todoA);

        Todo extracted = todoRepository.findById(todoA.getId())
                .orElseGet(() -> Todo.of("기본", TodoType.ETC));
        assertThat(todoA.getId()).isEqualTo(extracted.getId());
        assertThat(todoA.getContent()).isEqualTo(extracted.getContent());
        assertThat(todoA.getType()).isEqualTo(extracted.getType());
    }
}