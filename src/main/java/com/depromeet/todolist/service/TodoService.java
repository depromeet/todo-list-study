package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.TodoRequest;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addItem(TodoRequest todoRequest) {
        Todo todo = Todo.builder()
                .title(todoRequest.getTitle())
                .order(todoRequest.getOrder())
                .completed(todoRequest.getCompleted())
                .build();

        return todoRepository.save(todo);
    }

    public Todo searchById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Todo> searchAll() {
        return todoRepository.findAll();
    }

    public Todo updateById(Long id, TodoRequest todoRequest) {
        Todo todo = searchById(id);
        todo.updateTodo(todoRequest.getTitle(), todoRequest.getOrder(), todoRequest.getCompleted());
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
