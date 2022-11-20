package com.depromeet.todolist.todo.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.todolist.error.CustomException;
import com.depromeet.todolist.error.ErrorCode;
import com.depromeet.todolist.todo.domain.Todo;
import com.depromeet.todolist.todo.infrastructure.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo createItem(String content, boolean finished) {
        return todoRepository.save(
                Todo.builder()
                        .content(content)
                        .finished(finished)
                        .build());
    }

    @Transactional(readOnly = true)
    public Todo getItem(UUID id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Todo> getList() {
        return todoRepository.findAll();
    }

    @Transactional
    public Todo updateItem(UUID id, String content, boolean finished) {
        var todo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

        todo.updateContent(content).updateFinished(finished);

        return todo;
    }

    @Transactional
    public void deleteItem(UUID id) {
        var todo = todoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

        todoRepository.delete(todo);
    }
}
