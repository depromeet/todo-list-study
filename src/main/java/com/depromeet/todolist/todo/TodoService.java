package com.depromeet.todolist.todo;

import com.depromeet.todolist.todo.dto.TodoRequest;
import com.depromeet.todolist.todo.dto.TodoResponse;
import com.depromeet.todolist.todo.dto.TodoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public TodoResponseDto findAll() {

        List<TodoResponse> todoResponses = todoRepository.findAll()
                .stream()
                .map(todo -> new TodoResponse(todo.getContent(), todo.getType()))
                .collect(Collectors.toList());

        return new TodoResponseDto(todoResponses);
    }

    @Transactional(readOnly = true)
    public TodoResponse find(Long todoId) {
        Todo todo = findById(todoId);
        return new TodoResponse(todo.getContent(), todo.getType());
    }

    @Transactional(readOnly = true)
    public Todo findById(Long todoId) {

        return todoRepository.findById(todoId)
                .orElseThrow(() -> {
                    log.debug("There is no Todo(id) : {}", todoId);
                    return new IllegalArgumentException();
                });
    }

    @Transactional
    public void deleteById(Long todoId) {
        findById(todoId).delete();
    }

    @Transactional
    public void updateById(Long todoId, TodoRequest todoRequest) {
        findById(todoId).update(todoRequest.getContent(), todoRequest.getType());
    }

    // COMMENT: 메서드 depth(괄호 갯수)에 대한 고민
    @Transactional
    public TodoResponse save(TodoRequest todoRequest) {
        Todo todo = todoRepository.save(Todo.of(todoRequest.getContent(), todoRequest.getType()));
        return new TodoResponse(todo.getContent(), todo.getType());
    }
}
