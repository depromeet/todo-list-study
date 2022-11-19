package com.depromeet.todolist.todo.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.depromeet.todolist.todo.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
